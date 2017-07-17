package com.zadi.kosakatabahasaarab.Search.Recycler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zadi.kosakatabahasaarab.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Muh. Zadi on 7/13/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder>{
private MediaPlayer mp3;
    private AssetManager assetManager;
     Context c;
    ArrayList<HashMap<String, String>> names;

    public MyAdapter(Context c, ArrayList<HashMap<String, String>> names) {
        this.c = c;
        this.names = names;
        assetManager =c.getAssets();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.nameTxt.setText(names.get(position).get("indonesia"));
        holder.nameTxt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                LayoutInflater mInflater = LayoutInflater.from(c);
                View v = mInflater.inflate(R.layout.zoom_result_search, null);
                ImageView zoomImage = (ImageView)v.findViewById(R.id.zoomImage);
                TextView txtIndo = (TextView)v.findViewById(R.id.txtIndo);
                Button btnArab = (Button)v.findViewById(R.id.btnArab);
                final AlertDialog dialog = new AlertDialog.Builder(c).create();
                dialog.setView(v);
                dialog.setTitle("Hasil pencarian");
                dialog.setInverseBackgroundForced(true);
                dialog.setIcon(R.mipmap.icon);
                //CUSTOM FONTS
              Typeface faceIndo = Typeface.createFromAsset(assetManager, "fonts/ARNOPRO-REGULAR.OTF");
                Typeface faceArab = Typeface.createFromAsset(assetManager, "fonts/MAJALLA.TTF");
                txtIndo.setTypeface(faceIndo);
                btnArab.setTypeface(faceArab);

                Glide.with(c)
                        .load("http://192.168.43.228/kosakata/images/" + names.get(position).get("image"))
                        .crossFade()
                        .placeholder(R.mipmap.no_available)
                        .into(zoomImage);
                txtIndo.setText(names.get(position).get("indonesia"));
                btnArab.setText(Html.fromHtml(names.get(position).get("arab")));


                btnArab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String voice = "http://192.168.43.228/kosakata/voices/" + names.get(position).get("voice");
                        mp3 = new MediaPlayer();
                        mp3.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try{
                            mp3.setDataSource(voice);
                            mp3.prepare();
                        }catch (IllegalArgumentException e){
                            e.printStackTrace();
                        }catch (SecurityException e){
                            e.printStackTrace();
                        }catch (IllegalStateException e){
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        mp3.start();
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}
