package com.zadi.kosakatabahasaarab;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Menu_Awal extends AppCompatActivity implements View.OnClickListener {


    private Typeface btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__awal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.icon);

        Button kosakata = (Button) findViewById(R.id.btnKosaKata);
        Button quiz = (Button) findViewById(R.id.btnQuiz);

        //Font Custom
        btnMenu = Typeface.createFromAsset(getAssets(), "fonts/ARNOPRO-BOLDITALIC.OTF");
        kosakata.setTypeface(btnMenu);
        quiz.setTypeface(btnMenu);

        kosakata.setOnClickListener(this);
        quiz.setOnClickListener(this);

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, Menu_Awal.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int flags) {
        Intent intent = new Intent(context, Menu_Awal.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnKosaKata:
                startActivity(new Intent(this, KategoriKosakata.class));

                break;
            case R.id.btnQuiz:
                startActivity(new Intent(this, List_Kategori_Quiz.class));
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
                        finish();
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
        switch (id) {
            case R.id.cari:
                startActivity(new Intent(this, com.zadi.kosakatabahasaarab.Search.Searching.class));
                break;
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
                                Menu_Awal.this.finish();
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
