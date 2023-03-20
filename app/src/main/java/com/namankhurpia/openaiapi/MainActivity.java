package com.namankhurpia.openaiapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.theokanning.openai.OpenAiHttpException;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ImageButton showcontent;
    ImageView imageView;
    EditText editText;

    String open_ai_key = "";
    String res="";
    String prompt = "";
    LottieAnimationView animationView;
    LottieAnimationView error404;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        open_ai_key = prefs.getString("OPEN_AI_KEY", "none");

        showcontent = (ImageButton)findViewById(R.id.showcontent);
        imageView = (ImageView)findViewById(R.id.image);
        editText = (EditText)findViewById(R.id.prompt);
        animationView = (LottieAnimationView)findViewById(R.id.animationView);
        View parentLayout = findViewById(android.R.id.content);
        error404 = (LottieAnimationView) findViewById(R.id.offlineanimation);

        showcontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prompt = editText.getText().toString().trim();
                if (checkForInternet.getInstance(getApplicationContext()).isOnline()) {
                    error404.setVisibility(View.INVISIBLE);

                    if (!TextUtils.isEmpty(prompt)) {
                        try {
                            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        animationView.setVisibility(View.VISIBLE);
                        imageView.setImageBitmap(null);

                        Snackbar.make(parentLayout, "Fetching content", Snackbar.LENGTH_SHORT).show();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    RetrieveTask task = new RetrieveTask();
                                    res = task.doInBackground(prompt, open_ai_key);
                                    System.out.println("OUT -" + res);
                                    new DownloadImageTask((ImageView) findViewById(R.id.image), (LottieAnimationView) findViewById(R.id.animationView)).execute(res);
                                }
                                catch(OpenAiHttpException e)
                                {
                                    Snackbar.make(parentLayout, "Your text is violating Open-AI policy, Retry with some different words.", Snackbar.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                                catch (Exception e)
                                {
                                    Snackbar.make(parentLayout, "Socket Exception, Retry Please", Snackbar.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                                finally {
                                    animationView.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            animationView.setVisibility(View.INVISIBLE);
                                        }
                                    });
                                }


                            }

                        }).start();


                    } else {
                        Snackbar.make(parentLayout, "Prompt is empty", Snackbar.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    error404.setVisibility(View.VISIBLE);
                    Snackbar.make(parentLayout, "It seems you are offline", Snackbar.LENGTH_SHORT).show();
                }



            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(res))
                {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(res));
                    startActivity(browserIntent);
                }

            }
        });







    }
}