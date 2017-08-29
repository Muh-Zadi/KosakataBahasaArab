package com.zadi.kosakatabahasaarab;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Bantuan extends AppCompatActivity {
    private TextView txt, txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang__app__dan__bantuan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txt=(TextView)findViewById(R.id.txt1);
        txt.setText("1. Buka aplikasi\n"
                + "2. Tekan tombol Play untuk mulai main\n"
                + "3. Pilih tombol Kosakata untuk belajar kosakata setelahnya pilih kategori kosaka yang cocok untuk anda\n"
                + "4. Pilih tombol Quiz untuk menguji kekuatan ingatan anda (soal-soal)\n"
                + "5. Pilih icon pencarian di pojok kanan sebelah kiri menu bar untuk mencari kata yang diinginkan");

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/ARNOPRO-REGULAR.OTF");
        txt.setTypeface(face);

    }

}
