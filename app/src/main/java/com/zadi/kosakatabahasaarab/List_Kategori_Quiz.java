package com.zadi.kosakatabahasaarab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zadi.kosakatabahasaarab.quiz.QuizAlatSekolah;
import com.zadi.kosakatabahasaarab.quiz.QuizAnggotaBadan;
import com.zadi.kosakatabahasaarab.quiz.QuizBuah;
import com.zadi.kosakatabahasaarab.quiz.QuizBinatang;
import com.zadi.kosakatabahasaarab.quiz.QuizKeluarga;
import com.zadi.kosakatabahasaarab.quiz.QuizTumbuhan;


public class List_Kategori_Quiz extends AppCompatActivity {
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
        setContentView(R.layout.activity_kategori_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AdapaterListQuiz adapter = new AdapaterListQuiz(this, menuItem, menuImage);
        listView = (ListView)findViewById(R.id.mobilelist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                if (position == 0)
                {
                   intent = new Intent(parent.getContext(), QuizBuah.class);
                    startActivity(intent);
                }
                else if (position == 1)
                {
                    intent = new Intent(parent.getContext(), QuizBinatang.class);
                    startActivity(intent);
                }
                else if (position == 2)
                {
                     intent = new Intent(parent.getContext(), QuizKeluarga.class);
                    startActivity(intent);
                }
                else if (position == 3)
                {
                     intent = new Intent(parent.getContext(), QuizTumbuhan.class);
                    startActivity(intent);
                }
                else if (position == 4)
                {
                    intent = new Intent(parent.getContext(), QuizAnggotaBadan.class);
                    startActivity(intent);
                }
                else if (position == 5)
                {
                     intent = new Intent(parent.getContext(), QuizAlatSekolah.class);
                     startActivity(intent);
                }



            }
        });


    }

}
