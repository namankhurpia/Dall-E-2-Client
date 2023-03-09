package com.namankhurpia.openaiapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button showcontent;
    ImageView imageView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.processing);
        showcontent = (Button)findViewById(R.id.showcontent);
        imageView = (ImageView)findViewById(R.id.image);
        editText = (EditText)findViewById(R.id.prompt);


        showcontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String prompt = editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RetrieveTask task = new RetrieveTask();
                        String res = task.doInBackground(prompt);
                        System.out.println("OUT -"+res);
                        new DownloadImageTask((ImageView) findViewById(R.id.image))
                                .execute(res);
                    }
                }).start();


            }
        });







    }
}