package com.zadi.kosakatabahasaarab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zadi.kosakatabahasaarab.kosakata.KosakataAlatSekolah;
import com.zadi.kosakatabahasaarab.kosakata.KosakataBuah;
import com.zadi.kosakatabahasaarab.kosakata.Kosakata_Anggota_Badan;
import com.zadi.kosakatabahasaarab.kosakata.Kosakata_Binatang;
import com.zadi.kosakatabahasaarab.kosakata.Kosakata_Keluarga;
import com.zadi.kosakatabahasaarab.kosakata.Kosakata_Tumbuhan;

public class KategoriKosakata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kategori_kosakata);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void click_buah_buahan(View v) {
        Intent panggil_class = new Intent (this,KosakataBuah.class );//intent untuk memanggi activity buah_buahan
        startActivity(panggil_class); 							//saat imagebutton di klik di menu(activity_kosa_kat)

    }
    public void click_binatang(View v){
        Intent panggil_class = new Intent(this,Kosakata_Binatang.class);
        startActivity(panggil_class);
    }
    public void click_keluarga(View v) {
        Intent panggil_class = new Intent(this,Kosakata_Keluarga.class);
        startActivity(panggil_class);
    }
    public void click_tumbuhan(View v) {
        Intent panggil_class = new Intent(this,Kosakata_Tumbuhan.class);
        startActivity(panggil_class);
    }
    public void click_anggota_badan(View v) {
        Intent panggil_class = new Intent(this,Kosakata_Anggota_Badan.class);
        startActivity(panggil_class);
    }
    public void click_alat_sekolah(View v) {
        Intent panggil_class = new Intent(this,KosakataAlatSekolah.class);
        startActivity(panggil_class);
    }

}
