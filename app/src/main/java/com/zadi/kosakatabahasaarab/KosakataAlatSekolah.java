package com.zadi.kosakatabahasaarab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class KosakataAlatSekolah extends AppCompatActivity {
    public static String url = "http://192.168.43.228/kosakata/getdata_alatsekolah.php";
    private ProgressDialog pDialog;
    private TextView  txtIndo, txtArab;
    private RecyclerView listRecycleView;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ImageView imgs;
    private Typeface faceArab, faceIndo;
    static final int tampil_error=1;

    ArrayList<HashMap<String, String>> list_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kosakata);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.icon);


        listRecycleView = (RecyclerView)findViewById(R.id.listRecycleView);
        imgs=(ImageView) findViewById(R.id.imgViewOne);
        txtIndo = (TextView)findViewById(R.id.txtIndo);
        txtArab = (TextView)findViewById(R.id.txtArab);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        listRecycleView.setLayoutManager(llm);
        requestQueue = Volley.newRequestQueue(KosakataAlatSekolah.this);
        list_data = new ArrayList<HashMap<String, String>>();

        //Costom fonts
        faceArab = Typeface.createFromAsset(getAssets(), "fonts/MAJALLA.TTF");
        faceIndo = Typeface.createFromAsset(getAssets(), "fonts/ARNOPRO-REGULAR.OTF");
        txtArab.setTypeface(faceArab);
        txtIndo.setTypeface(faceIndo);

        //handle error saat tidak terkoneksi ke internet
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){
            //saat ada koneksi
            new KosakataAlatSekolah.PrefechData().execute();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                //Log.d("arab", new String(response, "UTF-8"));
                Log.d("response ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("kosakata");
                    for (int a = 0; a < jsonArray.length(); a++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("id_kosakata", json.getString("id_kosakata"));
                        map.put("indonesia", json.getString("indonesia"));
                        map.put("arab", json.getString("arab"));
                        map.put("image", json.getString("image"));
                        map.put("voice", json.getString("voice"));
                        map.put("category", json.getString("category"));
                        list_data.add(map);
                        AdapterList_Alat_Sekolah adapter =  new AdapterList_Alat_Sekolah(KosakataAlatSekolah.this, list_data, imgs,txtIndo,txtArab);
                        listRecycleView.setAdapter(adapter);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KosakataAlatSekolah.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
        }else {
            showDialog(tampil_error);
        }

    }
    //Splash yang di gunakan untuk mengambil data dari server
    class PrefechData extends AsyncTask<Void, Void, Void> {
        ProgressDialog progress = new ProgressDialog(KosakataAlatSekolah.this);

        public PrefechData(){
            progress.setTitle(KosakataAlatSekolah.this.getString(R.string.app_name));
            progress.setCancelable(false);
            progress.setCanceledOnTouchOutside(false);
            progress.setMessage("Please wait...");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.d("JSON",JSONTools.getJSON(url).toString());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(progress.isShowing()){
                progress.dismiss();
                imgs.setImageResource(R.mipmap.buku_awal);
                txtIndo.setText("Buku");
                txtArab.setText("كِتَابٌ");
                super.onPostExecute(result);
            }
        }
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id){
            case tampil_error:
                AlertDialog.Builder errorDialog = new AlertDialog.Builder(this);
                errorDialog.setTitle("Koneksi Error");
                errorDialog.setMessage("Anda tidak terhubung ke internet");
                errorDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        Intent exit = new Intent(Intent.ACTION_MAIN);
                        exit.addCategory(Intent.CATEGORY_HOME);
                        exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        KosakataAlatSekolah.this.finish();
                        //startActivity(exit);

                    }
                }).show();
                AlertDialog errorAlert = errorDialog.create();
                return errorAlert;
            default:
                break;
        }
        return dialog;
    }

}
