package com.zadi.kosakatabahasaarab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class List_Quiz extends AppCompatActivity {
    String[] menuItem = {"Buah - buahan","Binatang","keluarga","Tumbuhan","Anggota badan","Alat sekolah"};
    Integer[] menuImage = {
            R.mipmap.apel,
            R.mipmap.kucing,
            R.mipmap.sepupu,
            R.mipmap.tomat,
            R.mipmap.dagu,
            R.mipmap.buku};


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CostumListAdapater adapter = new CostumListAdapater(this, menuItem, menuImage);
        listView = (ListView)findViewById(R.id.mobilelist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                if (position == 0)
                {
                   //intent = new Intent(parent.getContext(), CobaQuiz.class);
                    //startActivity(intent);
                }
                else if (position == 1)
                {
                    // intent = new Intent(parent.getContext(), Quiz2.class);                    	startActivity(intent);
                }
                else if (position == 2)
                {
                    // intent = new Intent(parent.getContext(), Quiz3.class);                    	startActivity(intent);
                }
                else if (position == 3)
                {
                    // intent = new Intent(parent.getContext(), Quiz4.class);                    	startActivity(intent);
                }
                else if (position == 4)
                {
                    // intent = new Intent(parent.getContext(), Quiz5.class);                   	startActivity(intent);
                }
                else if (position == 5)
                {
                    // intent = new Intent(parent.getContext(), Quiz6.class);                   	startActivity(intent);
                }



            }
        });


    }

}
