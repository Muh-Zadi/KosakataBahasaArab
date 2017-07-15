package com.zadi.kosakatabahasaarab;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Muh. Zadi on 7/5/2017.
 */

public class JSONTools {
    public static JSONObject getJSON(String url){
        JSONObject json = null;
        try{
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();

            BufferedReader br = new BufferedReader(new InputStreamReader(is), 8);
            StringBuilder builder = new StringBuilder();
            String line =null;
            while ((line = br.readLine()) != null){
                builder.append(line + "\n");
            }
            is.close();
            json = new JSONObject(builder.toString());
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
