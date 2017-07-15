package com.zadi.kosakatabahasaarab;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Menu_Awal extends AppCompatActivity implements View.OnClickListener {


    private Typeface btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__awal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.icon);

        Button kosakata = (Button)findViewById(R.id.btnKosaKata);
        Button quiz = (Button)findViewById(R.id.btnQuiz);

        //Font Custom
        btnMenu = Typeface.createFromAsset(getAssets(), "fonts/ARNOPRO-BOLDITALIC.OTF");
        kosakata.setTypeface(btnMenu);
        quiz.setTypeface(btnMenu);

        kosakata.setOnClickListener(this);
        quiz.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnKosaKata:
                startActivity(new Intent(this, MenuKosakata.class));

                break;
            case R.id.btnQuiz:
                startActivity(new Intent(this, List_Quiz.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tutup Aplikasi?")
                .setCancelable(false).setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        Intent exit = new Intent(
                                Intent.ACTION_MAIN);
                        exit.addCategory(Intent.CATEGORY_HOME);
                        exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(exit);
                    }
                }).setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.kosa_kata, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            //case R.id.cari:
            //	startActivity(new Intent(this, Pencarian.class));
            //	break;
            case R.id.tentang_aplikasi:
                startActivity(new Intent(this, Tentang_App.class));
                break;
            case R.id.about:
                startActivity(new Intent(this, About.class));
                break;
            case R.id.bantuan:
                startActivity(new Intent(this, Bantuan.class));
                break;
            case R.id.exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Tutup Aplikasi?")
                        .setCancelable(false).setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                Intent exit = new Intent(
                                        Intent.ACTION_MAIN);
                                exit.addCategory(Intent.CATEGORY_HOME);
                                exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(exit);
                            }
                        }).setNegativeButton("Tidak",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        }).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
