package com.zadi.kosakatabahasaarab;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Tentang_App extends AppCompatActivity {
    private TextView txt, txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang__app__dan__bantuan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.icon);
        txt=(TextView)findViewById(R.id.txt1);
        //	txt2 = (TextView)findViewById(R.id.txt2);
        txt.setText("\t\tAplikasi ini di buat untuk anak-anak"
                + "(SD) sederajat. Pada aplikasi ini di lengkapi beberapa fitur"
                + " diantaranya teks bahasa indonesia, bahasa arab juga adanya gambar "
                + "serta voice(suara) agar hal tersebut dapat memperkuat"
                + " hafalan atau ingatan anak-anak dalam kosakata-kosakata yang "
                + "telah di pelajarinya.");
        //txt2.setText("Created By Muh. Zadi\n "+"AKADEMI KOMUNITAS NEGERI SUMENEP");

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/ARNOPRO-REGULAR.OTF");
        txt.setTypeface(face);
        //txt2.setTypeface(face);

    }

}
