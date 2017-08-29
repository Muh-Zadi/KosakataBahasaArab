package com.zadi.kosakatabahasaarab.Search;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zadi.kosakatabahasaarab.R;
import com.zadi.kosakatabahasaarab.Search.MySQL.SenderReceiver;
import com.zadi.kosakatabahasaarab.config.Config;

public class Searching extends AppCompatActivity {
    RecyclerView rv;
    SearchView sv;
    ImageView noDataImg, noNetworkImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        sv = (SearchView)findViewById(R.id.sv);
        noDataImg= (ImageView)findViewById(R.id.nodataImg);
        noNetworkImg = (ImageView)findViewById(R.id.noserver);



        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                SenderReceiver sr=new SenderReceiver(Searching.this,query, Config.url_search,rv,noDataImg,noNetworkImg);

                sr.execute();
                return false;

            }
        });

    }

}
