package com.namankhurpia.openaiapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Toast;

public class Web_View extends AppCompatActivity {

    String URL = "https://platform.openai.com/account/api-keys";
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_web_view);



        mWebView =(WebView)findViewById(R.id.webview);

        mWebView.getSettings().setJavaScriptEnabled(true);
        Toast.makeText(getApplicationContext(), "Images may take time to load",Toast.LENGTH_LONG).show();
        mWebView.loadUrl(URL);

    }
}