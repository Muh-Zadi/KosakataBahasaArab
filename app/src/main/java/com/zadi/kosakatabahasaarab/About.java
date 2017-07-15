package com.zadi.kosakatabahasaarab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.icon);

        String url = "file:///android_asset/www/about.html";//Pendefinisian URL
        WebView view = (WebView)findViewById(R.id.webView1);//sinkronisasi object berdasarkan id
        view.getSettings().setJavaScriptEnabled(true);//untuk mengaktifkan javascript
        view.loadUrl(url);//agar URL terload saat dibuka aplikasi
    }

}
