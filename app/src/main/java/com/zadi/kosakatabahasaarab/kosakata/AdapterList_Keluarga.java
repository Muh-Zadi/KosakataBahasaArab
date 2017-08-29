package com.zadi.kosakatabahasaarab.kosakata;

/**
 * Created by Muh. Zadi on 7/4/2017.
 */
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;

import com.zadi.kosakatabahasaarab.R;
import com.zadi.kosakatabahasaarab.config.Config;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AdapterList_Keluarga extends RecyclerView.Adapter<AdapterList_Keluarga.ViewHolder> {
    Context context;
    ImageView imgViewOne;
    private TextView txtIndo, txtArab;
    private MediaPlayer mp3;
    ArrayList<HashMap<String, String >> list_data;
    public AdapterList_Keluarga(Kosakata_Keluarga mainActivity, ArrayList<HashMap<String, String >>list_data, ImageView imgs, TextView txtindo, TextView txtarab){
        this.context = mainActivity;
        this.list_data = list_data;
        this.imgViewOne=imgs;
        this.txtArab=txtarab;
        this.txtIndo=txtindo;
    }
    @Override
    public AdapterList_Keluarga.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_images, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterList_Keluarga.ViewHolder holder, final int position) {

        //Displaying to list with Recycle View
        Glide.with(context)
                .load(Config.TAG_IMAGES_KOSAKATA + list_data.get(position).get("image"))
                .crossFade()
                .placeholder(R.mipmap.no_available)
                .into(holder.listImage);
        //Action saat gambar di list di klick
        holder.listImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(context)
                        .load(Config.TAG_IMAGES_KOSAKATA + list_data.get(position).get("image"))
                        .crossFade()
                        .placeholder(R.mipmap.no_available)
                        .into(imgViewOne);
                txtIndo.setText(list_data.get(position).get("indonesia"));
                txtArab.setText(Html.fromHtml(list_data.get(position).get("arab")));
                final String voice = Config.TAG_VOICES + list_data.get(position).get("voice");
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
        HashMap<String, String> data = list_data.get(position);
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView listImage;
        public ViewHolder(final View itemView) {
            super(itemView);
            listImage = (ImageView) itemView.findViewById(R.id.listImage);
        }
    }
}
