package com.example.rahman.webviewapps;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private SwipeRefreshLayout swif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swif=findViewById(R.id.swif);
        swif.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
            }
        });
        load();

    }
    public void load()
    {
        webView=findViewById(R.id.webviewId);
        webView.loadUrl("https://www.w3schools.com/");
        WebSettings settings=webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_INSET);
        webView.setScrollbarFadingEnabled(true);
        webView.setWebViewClient(new WebViewClient()
                                 {
                                     @Override
                                     public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                       webView.loadUrl("file:///android_asset/hello.html");
                                     }

                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                       swif.setRefreshing(false);
                                     }
                                 }



        );
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
        {
            webView.goBack();
        }
        else
        {
            finish();
        }
    }
}
