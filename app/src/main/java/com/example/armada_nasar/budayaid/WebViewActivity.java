package com.example.armada_nasar.budayaid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private final String url = "http://google.com/search?q=";
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView =  (WebView) findViewById(R.id.webview);

        Bundle params = getIntent().getExtras();
        String query = params.getString("query");
        String pageTitle = params.getString("title");

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url + query);
        webView.getSettings().setJavaScriptEnabled(true);

        setTitle(pageTitle);
    }
}
