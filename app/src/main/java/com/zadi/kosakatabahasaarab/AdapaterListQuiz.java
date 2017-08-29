package com.zadi.kosakatabahasaarab;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class AdapaterListQuiz extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] menuItem;
   
    private final Integer[] menuGambar;

    private AssetManager assetManager;

    public AdapaterListQuiz(Activity context, String[] menuItem, Integer[] menuGambar) {
        super(context, R.layout.activity_kategori_quiz, menuItem);
        this.context = context;
        this.menuItem = menuItem;
        this.menuGambar = menuGambar;
        assetManager = context.getAssets();
        
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_view_kategori_quiz, null, true);

        TextView textMenu = (TextView)rowView.findViewById(R.id.menuItem);
      

       
        ImageView imgMenu = (ImageView)rowView.findViewById(R.id.imageView);

        textMenu.setText(menuItem[position]);
       
        imgMenu.setImageResource(menuGambar[position]);
        Typeface face = Typeface.createFromAsset(assetManager, "fonts/ARNOPRO-ITALIC.OTF");
		textMenu.setTypeface(face);
        return rowView;
    }
}