package com.namankhurpia.openaiapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {

    TextView portfolio, checksource, privacypolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about_us);

        portfolio = (TextView)findViewById(R.id.portfolio);
        checksource = (TextView) findViewById(R.id.sourcecode);
        privacypolicy = (TextView) findViewById(R.id.privacypolicy);

        portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("http://namank.xyz/")));
            }
        });

        checksource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://platform.openai.com/account/api-keys")));
            }
        });

        privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("http://namank.xyz/")));
            }
        });

    }
}