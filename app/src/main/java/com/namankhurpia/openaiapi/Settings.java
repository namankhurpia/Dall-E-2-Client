package com.namankhurpia.openaiapi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

public class Settings extends AppCompatActivity {

    ImageButton getPersonalKey, addPerSonalKey;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        getPersonalKey = (ImageButton) findViewById(R.id.getpersonalkey);
        addPerSonalKey = (ImageButton) findViewById(R.id.addpersonalkey);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Enter your OpenAI Key");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);



        getPersonalKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://platform.openai.com/account/api-keys"));
                startActivity(browserIntent);

            }
        });

        addPerSonalKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setView(input);
                builder.show();
            }
        });

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                key = input.getText().toString();
                if(input.getParent() != null) {
                    ((ViewGroup)input.getParent()).removeView(input);
                }

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("OPEN_AI_KEY", key);
                edit.commit();

                startActivity(new Intent(Settings.this, Home.class));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                if(input.getParent() != null) {
                    ((ViewGroup)input.getParent()).removeView(input);
                }
            }
        });




    }
}