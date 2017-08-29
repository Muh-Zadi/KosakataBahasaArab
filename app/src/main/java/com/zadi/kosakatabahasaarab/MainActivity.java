package com.zadi.kosakatabahasaarab;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageButton btnPlay = (ImageButton)findViewById(R.id.btnPlay);
        //Membuat animasi
        View a = (View)findViewById(R.id.animasi);
        Animation splashAni = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        a.startAnimation(splashAni);
        TextView txt = (TextView)findViewById(R.id.txtAyo);
        TextView txt2 = (TextView)findViewById(R.id.txtKosakata);
        TextView txt3 = (TextView)findViewById(R.id.txtArab);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/ARNOPRO-ITALIC.OTF");
        txt.setTypeface(face);
        txt2.setTypeface(face);
        txt3.setTypeface(face);
        playSound();
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent panggil = new Intent(MainActivity.this, Menu_Awal.class);
                startActivity(panggil);

            }
        });
    }

        //VOICE

    @Override
    protected void onPause() {
        try{
            super.onPause();
            mp3.pause();
        }catch (Exception e){

        }

    }
    private void playSound(){
        try{
            if (mp3.isPlaying()){
                mp3.stop();
                mp3.release();
            }
        }catch(Exception e){
        }
        mp3=MediaPlayer.create(this, R.raw.play);
        mp3.setLooping(false);
        mp3.start();
    }

}


