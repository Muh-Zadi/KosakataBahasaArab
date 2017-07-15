package com.zadi.kosakatabahasaarab;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //getActionBar().hide();
        setContentView(R.layout.splash);
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
    }
    public void btnPlay(View v){
        Intent panggil = new Intent(this, Menu_Awal.class);
        startActivity(panggil);
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
            //Toast.makeText(this, "Masuk Exception", Toast.LENGTH_LONG).show();
        }

        mp3=MediaPlayer.create(this, R.raw.ajakan);
        mp3.setLooping(false);
        mp3.start();
    }
}
