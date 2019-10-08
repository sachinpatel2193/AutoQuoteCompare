package com.ankit_pro.autoquotecompare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class insuranceautowebview extends AppCompatActivity {

    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insuranceautowebview);

        webview=(WebView)findViewById(R.id.webview);

        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");                        // getting value of link from previous activity
        webview.setWebViewClient(new WebViewClient());                              // setting webviewclient

        webview.loadUrl(url);                                             // loadurl to open link in browser inside webview

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
    @Override
    public void onBackPressed() {                                   // if user navigates inside the website and press back button then user will not be redirected to app, user will be redirected to previous webpage
        if(webview.canGoBack()){
            webview.goBack();
        } else {
            super.onBackPressed();
        }

    }
}
