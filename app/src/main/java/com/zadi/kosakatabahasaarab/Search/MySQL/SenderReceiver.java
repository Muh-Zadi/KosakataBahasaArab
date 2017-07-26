package com.zadi.kosakatabahasaarab.Search.MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by Muh. Zadi on 7/13/2017.
 */

public class SenderReceiver extends AsyncTask<Void,Void,String> {

    Context c;
    String query;
    ProgressDialog pd;
    String urlAddress;
    RecyclerView rv;
    ImageView noDataImg,noNetworkImg;

    public SenderReceiver(Context c, String query, String urlAddress, RecyclerView rv, ImageView noDataImg, ImageView noNetworkImg) {
        this.c = c;
        this.query = query;
        this.urlAddress = urlAddress;
        this.rv = rv;

        this.noDataImg = noDataImg;
        this.noNetworkImg = noNetworkImg;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       pd=new ProgressDialog(c);
        pd.setTitle("Cari");
        pd.setMessage("Mohon tunggu...");
       // pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.sendAndReceive();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
       pd.dismiss();

        rv.setAdapter(null);

        if(result != null)
        {
            if(! result.contains("null"))
            {
                Parser p = new Parser(c,result,rv);
                p.execute();
            }else
            {
                noNetworkImg.setVisibility(View.INVISIBLE);
                noDataImg.setVisibility(View.INVISIBLE);
            }

        }else
        {
            noNetworkImg.setVisibility(View.VISIBLE);
            noDataImg.setVisibility(View.INVISIBLE);
        }
    }
    private String sendAndReceive()
    {
        HttpURLConnection con=Connector.connect(urlAddress);
        if(con==null){
            return null;
        }

        try {
            OutputStream os=con.getOutputStream();

            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new DataPackager(query).packData());

            bw.flush();

            //RELEASE RES
            bw.close();
            os.close();

            //RESPONSE ???
            int responseCode=con.getResponseCode();
            StringBuffer response =new StringBuffer();
            if(responseCode==con.HTTP_OK)
            {
                InputStream is =con.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));

                String line;
                if(br !=null)
                {
                    while ((line=br.readLine()) != null)
                    {
                        response.append(line+"\n");
                    }

                }else {
                    return null;
                }
                br.close();
                is.close();
                return response.toString();


            }else
            {
                return String.valueOf(responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}