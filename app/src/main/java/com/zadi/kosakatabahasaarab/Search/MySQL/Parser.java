package com.zadi.kosakatabahasaarab.Search.MySQL;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.zadi.kosakatabahasaarab.Search.Recycler.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Muh. Zadi on 7/13/2017.
 */

public class Parser extends AsyncTask<Void, Void, Integer> {
    String data;
    RecyclerView rv;
    Context c;
    //ArrayList<String> names=new ArrayList<>();
    ArrayList<HashMap<String, String>> names = new ArrayList<HashMap<String, String>>();

    public Parser(Context c, String data, RecyclerView rv) {
        this.data = data;
        this.rv = rv;
        this.c = c;
        //ArrayList<HashMap<String, String>> names = new ArrayList<HashMap<String, String>>();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if(integer==1)
        {
            rv.setAdapter(new MyAdapter(c,names));
        }else {
            Toast.makeText(c, "Unable to parse", Toast.LENGTH_SHORT).show();
        }
    }

    private int parse() {
        try {
            JSONArray ja=new JSONArray(data);
            JSONObject jo =null;

            names.clear();
            for(int i=0; i<ja.length(); i++)
            {
                jo=ja.getJSONObject(i);
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("id_kosakata", jo.getString("id_kosakata"));
                map.put("indonesia", jo.getString("indonesia"));
                map.put("arab", jo.getString("arab"));
                map.put("image", jo.getString("image"));
                map.put("voice", jo.getString("voice"));
                map.put("category", jo.getString("category"));
               // list_data.add(map);
                //String name=jo.getString("indonesia");
                names.add(map);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
return 1;
    }
}

