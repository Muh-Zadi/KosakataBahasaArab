package com.zadi.kosakatabahasaarab.quiz;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zadi.kosakatabahasaarab.R;
import com.zadi.kosakatabahasaarab.config.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QuizKeluarga extends AppCompatActivity {

    TextView txtNama, txtNo, txtWaktu, txtSoal;
    Button btnPrev, btnSelesai, btnNext;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4;
    ImageView img;
    EditText inputNama;
    int jawabanYgDiPilih[] = null;
    int jawabanYgBenar[] = null;
    boolean cekPertanyaan = false;
    int urutanPertanyaan = 0;
    List<Soal> listSoal;
    JSONArray soal = null;
    CounterClass mCountDownTimer;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listSoal = new ArrayList<Soal>();

        //binding
        img = (ImageView) findViewById(R.id.imageView1);
        txtNama = (TextView) findViewById(R.id.textViewNama);
        txtNo = (TextView) findViewById(R.id.textViewNo);
        txtWaktu = (TextView) findViewById(R.id.textViewWaktu);
        txtSoal = (TextView) findViewById(R.id.textViewSoal);
        btnPrev = (Button) findViewById(R.id.buttonPrev);
        btnSelesai = (Button) findViewById(R.id.buttonSelesai);
        btnNext = (Button) findViewById(R.id.buttonNext);
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        rb1 = (RadioButton) findViewById(R.id.radio0);
        rb2 = (RadioButton) findViewById(R.id.radio1);
        rb3 = (RadioButton) findViewById(R.id.radio2);
        rb4 = (RadioButton) findViewById(R.id.radio3);

        //CUSTOM FONT ARAB
        Typeface rb = Typeface.createFromAsset(getAssets(), "fonts/MAJALLA.TTF");
        rb1.setTypeface(rb);
        rb2.setTypeface(rb);
        rb3.setTypeface(rb);
        rb4.setTypeface(rb);

        //action
        btnSelesai.setOnClickListener(klikSelesai);
        btnPrev.setOnClickListener(klikSebelum);
        btnNext.setOnClickListener(klikBerikut);
        //handle error saat tidak terkoneksi ke internet
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){
            showInputUser();
        }else {
            dialog_error();
        }

    }
    private void dialog_error(){
        final AlertDialog.Builder errorDialog = new AlertDialog.Builder(this);
        errorDialog.setTitle("Koneksi Error");
        errorDialog.setMessage("Anda tidak terhubung ke internet");
        errorDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                QuizKeluarga.this.finish();
            }
        }).show();
    }
    private void showInputUser() {
        LayoutInflater mInflater = LayoutInflater.from(this);
        View v = mInflater.inflate(R.layout.input_user, null);
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(v);
        dialog.setTitle("Isikan Nama Anda");
        dialog.setIcon(R.mipmap.iconquiz);
        dialog.setCancelable(false);
        final Button btnOk = (Button) v.findViewById(R.id.btnOk);
        final Button btnCancel = (Button)v.findViewById(R.id.btnCancel);
        inputNama = (EditText) v.findViewById(R.id.inputID);
        btnOk.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (inputNama.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Isikan Nama Anda",
                            Toast.LENGTH_LONG).show();
                } else {
                    new GetSoal().execute();
                    txtNama.setText(inputNama.getText().toString());
                    dialog.dismiss();
                }

            }
        });
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizKeluarga.this.finish();
            }
        });

        dialog.show();

    }

    private class GetSoal extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(QuizKeluarga.this);
            pDialog.setMessage("Mohon tunggu...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(Config.url_soal_keluarga, ServiceHandler.GET);
            //   Log.d("response ", response);
            Log.d("Response: ", "> " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    soal = jsonObj.getJSONArray(Config.TAG_DAFTAR);
                    Soal s = null;
                    // looping through All Contacts
                    for (int i = 0; i < soal.length(); i++) {
                        JSONObject c = soal.getJSONObject(i);
                        s = new Soal();

                        String id = c.getString(Config.TAG_ID);
                        String soal = c.getString(Config.TAG_SOAL);
                        String a = c.getString(Config.TAG_A);
                        String b = c.getString(Config.TAG_B);
                        String cc = c.getString(Config.TAG_C);
                        String d = c.getString(Config.TAG_D);
                        String jwb = c.getString(Config.TAG_JWB);
                        String gambar = c.getString(Config.TAG_GAMBAR);

                        s.setId(id);
                        s.setSoal(soal);
                        s.setA(a);
                        s.setB(b);
                        s.setC(cc);
                        s.setD(d);
                        s.setJawban(jwb);
                        s.setGambar(gambar);
                        listSoal.add(s);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            jawabanYgDiPilih = new int[listSoal.size()];
            Arrays.fill(jawabanYgDiPilih, -1);
            jawabanYgBenar = new int[listSoal.size()];
            Arrays.fill(jawabanYgBenar, -1);
            setUpSoal();
        }
    }

    private void setUpSoal() {
        Collections.shuffle(listSoal);
        tunjukanPertanyaan(0, cekPertanyaan);
    }

    private void tunjukanPertanyaan(int urutan_soal_soal, boolean review) {
        if (urutan_soal_soal != listSoal.size()){
            btnSelesai.setTextColor(getResources().getColor(R.color.red));
            btnSelesai.setEnabled(false);
        }
        if(urutan_soal_soal == 0)
            setUpWaktu();

        try {
            rg.clearCheck();
            Soal soal = new Soal();
            soal = listSoal.get(urutan_soal_soal);
            if (jawabanYgBenar[urutan_soal_soal] == -1) {
                jawabanYgBenar[urutan_soal_soal] = Integer.parseInt(soal
                        .getJawban());
            }
            String soalnya = soal.getSoal();
            txtSoal.setText(soalnya);
            rg.check(-1);
            rb1.setTextColor(Color.WHITE);
            rb2.setTextColor(Color.WHITE);
            rb3.setTextColor(Color.WHITE);
            rb4.setTextColor(Color.WHITE);
            Picasso.with(getApplicationContext())
                    .load(Config.TAG_IMAGES_QUIZ + listSoal.get(urutan_soal_soal).getGambar())
                    .error(R.mipmap.no_available)
                    .into(img);
            rb1.setText(Html.fromHtml(soal.getA()));
            rb2.setText(Html.fromHtml(soal.getB()));
            rb3.setText(Html.fromHtml(soal.getC()));
            rb4.setText(Html.fromHtml(soal.getD()));

            Log.d("", jawabanYgDiPilih[urutan_soal_soal] + "");
            if (jawabanYgDiPilih[urutan_soal_soal] == 1)
                rg.check(R.id.radio0);
            if (jawabanYgDiPilih[urutan_soal_soal] == 2)
                rg.check(R.id.radio1);
            if (jawabanYgDiPilih[urutan_soal_soal] == 3)
                rg.check(R.id.radio2);
            if (jawabanYgDiPilih[urutan_soal_soal] == 4)
                rg.check(R.id.radio3);

            pasangLabelDanNomorUrut();

            if (urutan_soal_soal == (listSoal.size() - 1)) {
                btnSelesai.setEnabled(true);
                btnSelesai.setTextColor(getResources().getColor(R.color.white));
                btnNext.setTextColor(getResources().getColor(R.color.red));
                btnNext.setEnabled(false);
            }
            if (urutan_soal_soal == 0)
                btnPrev.setTextColor(getResources().getColor(R.color.red));
            btnPrev.setEnabled(false);


            if (urutan_soal_soal > 0)
                btnPrev.setTextColor(getResources().getColor(R.color.white));
            btnPrev.setEnabled(true);

            if (urutan_soal_soal < (listSoal.size() - 1))
                btnNext.setTextColor(getResources().getColor(R.color.white));
            btnNext.setEnabled(true);

            if (review) {
                mCountDownTimer.cancel();
                Log.d("periksa", jawabanYgDiPilih[urutan_soal_soal] + ""
                        + jawabanYgBenar[urutan_soal_soal]);
                //Jika jawaban yang dipilih salah, maka conbobox akan berwarna merah saat user memeilih action "Periksa"
                //Dan jika benar akan berwarna GREEN
                if (jawabanYgDiPilih[urutan_soal_soal] != jawabanYgBenar[urutan_soal_soal]) {
                    if (jawabanYgDiPilih[urutan_soal_soal] == 1)
                        rb1.setTextColor(Color.RED);
                    if (jawabanYgDiPilih[urutan_soal_soal] == 2)
                        rb2.setTextColor(Color.RED);
                    if (jawabanYgDiPilih[urutan_soal_soal] == 3)
                        rb3.setTextColor(Color.RED);
                    if (jawabanYgDiPilih[urutan_soal_soal] == 4)
                        rb4.setTextColor(Color.RED);
                }
                if (jawabanYgBenar[urutan_soal_soal] == 1)
                    rb1.setTextColor(Color.GREEN);
                if (jawabanYgBenar[urutan_soal_soal] == 2)
                    rb2.setTextColor(Color.GREEN);
                if (jawabanYgBenar[urutan_soal_soal] == 3)
                    rb3.setTextColor(Color.GREEN);
                if (jawabanYgBenar[urutan_soal_soal] == 4)
                    rb4.setTextColor(Color.GREEN);
            }

        } catch (Exception e) {
            Log.e(this.getClass().toString(), e.getMessage(), e.getCause());
        }
    }

    private OnClickListener klikSelesai = new OnClickListener() {
        public void onClick(View v) {
            aturJawaban_nya();
            // hitung berapa yg benar
            if ((rb1.isChecked() == false) && (rb2.isChecked() == false) && (rb3.isChecked() == false) && (rb4.isChecked() == false)) {
                Toast.makeText(getBaseContext(), "Pilih jawaban dulu", Toast.LENGTH_SHORT).show();
            } else {
                int jumlahJawabanYgBenar = 0;
                for (int i = 0; i < jawabanYgBenar.length; i++) {
                    if ((jawabanYgBenar[i] != -1)
                            && (jawabanYgBenar[i] == jawabanYgDiPilih[i]))
                        jumlahJawabanYgBenar++;
                }
                AlertDialog tampilKotakAlert;
                tampilKotakAlert = new AlertDialog.Builder(QuizKeluarga.this).create();
                tampilKotakAlert.setTitle("Hasil");
                tampilKotakAlert.setCancelable(false);
                tampilKotakAlert.setIcon(R.mipmap.iconquiz);
                tampilKotakAlert.setMessage("Benar " + jumlahJawabanYgBenar + " dari " + (listSoal.size() + " Soal \n\n\n" +"Score Anda "+ jumlahJawabanYgBenar * 10));

                tampilKotakAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Lagi",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                mCountDownTimer.cancel();
                                Arrays.fill(jawabanYgDiPilih, -1);
                                cekPertanyaan = false;
                                urutanPertanyaan = 0;
                                tunjukanPertanyaan(0, cekPertanyaan);
                            }
                        });

                tampilKotakAlert.setButton(AlertDialog.BUTTON_POSITIVE, "Periksa",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                mCountDownTimer.cancel();
                                cekPertanyaan = true;
                                urutanPertanyaan = 0;
                                tunjukanPertanyaan(0, cekPertanyaan);
                            }
                        });

                tampilKotakAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "Keluar",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                mCountDownTimer.cancel();
                                cekPertanyaan = false;
                                finish();
                            }
                        });

                tampilKotakAlert.show();

            }
        }
    };

    private void aturJawaban_nya() {
        if (rb1.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 1;
        if (rb2.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 2;
        if (rb3.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 3;
        if (rb4.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 4;

        Log.d("", Arrays.toString(jawabanYgDiPilih));
        Log.d("", Arrays.toString(jawabanYgBenar));

    }

    private OnClickListener klikBerikut = new OnClickListener() {
        public void onClick(View v) {
            aturJawaban_nya();
            if ((rb1.isChecked()==false) && (rb2.isChecked()==false) && (rb3.isChecked()==false) && (rb4.isChecked()==false)){
                Toast.makeText(getBaseContext(), "Pilih jawaban dulu", Toast.LENGTH_SHORT).show();
            }
            else
            {
                urutanPertanyaan++;
                if (urutanPertanyaan >= listSoal.size())
                    urutanPertanyaan = listSoal.size() - 1;

                tunjukanPertanyaan(urutanPertanyaan, cekPertanyaan);
            }
        }
    };

    private OnClickListener klikSebelum = new OnClickListener() {
        public void onClick(View v) {
            aturJawaban_nya();
            urutanPertanyaan--;
            if (urutanPertanyaan < 0)
                urutanPertanyaan = 0;

            tunjukanPertanyaan(urutanPertanyaan, cekPertanyaan);
        }
    };

    private void pasangLabelDanNomorUrut() {
        txtNo.setText("No. " + (urutanPertanyaan + 1)+ " dari "
                + listSoal.size() + " soal");
    }

    private void setUpWaktu() {
        mCountDownTimer = new CounterClass(240000, 1000);
        mCountDownTimer.start();
    }

    @SuppressLint("DefaultLocale")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            finish();
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            txtWaktu.setText(hms);
        }
    }

}
