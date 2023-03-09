package com.namankhurpia.openaiapi;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}