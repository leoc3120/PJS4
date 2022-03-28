package com.example.pjs4.repository;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pjs4.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APILoader extends AppCompatActivity {

    public void connexion(TextView label, TextView diet, ImageView img, Button btn, EditText entree, Button site){
        OkHttpClient client = new OkHttpClient();
        ArrayList<JSONObject> idList = new ArrayList<JSONObject>();
        Thread thread = new Thread(new Runnable() {

            Request request = new Request.Builder()
                    .url("https://edamam-recipe-search.p.rapidapi.com/search?q=" + entree.getEditableText())
                    .get()
                    .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
                    .addHeader("X-RapidAPI-Key", "7eb892f52bmsh3efccf80bdf39f1p1c2708jsn5628d01eca98")
                    .build();


            @Override
            public void run() {
                List<String> arrayy;
                String url2 = null;
                try  {
                    Response response = null;
                    response = client.newCall(request).execute();

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    JsonParser jp = new JsonParser();
                    JsonElement je = jp.parse(response.body().string());
                    String prettyJsonString = gson.toJson(je);

                    JSONObject jsonArray = new JSONObject(prettyJsonString);
                    JSONArray jr = jsonArray.getJSONArray("hits");


                    for (int i = 0; i < jr.length(); i++) {
                        idList.add(jr.getJSONObject(i));
                    }

                    arrayy = Arrays.asList(idList.get(0).toString().split(","));
                    //Log.d("TAG0", arrayy.get(4));
                    //Picasso.get().load(arrayy.get(2).replace("\"image\":\"", "").replace("\"", "").replace("\\/", "/")).into(img);

                    label.setText(arrayy.get(1).replace("\"label\":", "").replace("\"", "").replace("\"", ""));
                    diet.setText(arrayy.get(7).replace("\"dietLabels\":[", "").replace("]", "").replace("\"", "").replace("\"", ""));

                    URL url = new URL(arrayy.get(2).replace("\"image\":\"", "").replace("\"", "").replace("\\/", "/"));
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    APILoader.this.runOnUiThread(new Runnable() {
                        public void run() {
                            img.setImageBitmap(bmp);
                        }
                    });

                    site.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(arrayy.get(4).replace("\"url\":", "").replace("\"", "").replace("\\/", "/")));
                            Log.d("TAG0", arrayy.get(4).replace("\"url\":", "").replace("\"", "").replace("\\/", "/"));
                            startActivity(intent);
                        }
                    });

                    btn.setOnClickListener(new View.OnClickListener() {
                        int i = 1;
                        @Override
                        public void onClick(View view) {

                                List<String> newarrayy = Arrays.asList(idList.get(i).toString().split(","));

                            //while(i < newarrayy.size()) {
                            Picasso.get().load(newarrayy.get(2).replace("\"image\":\"", "").replace("\"", "").replace("\\/", "/")).into(img);
                            label.setText(newarrayy.get(1).replace("\"label\":", "").replace("\"", "").replace("\"", ""));
                            diet.setText(newarrayy.get(7).replace("\"dietLabels\":[", "").replace("]", "").replace("\"", "").replace("\"", ""));
                            i++;

                            site.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(newarrayy.get(4).replace("\"url\":", "").replace("\"", "").replace("\\/", "/")));
                                    Log.d("TAG0", newarrayy.get(4).replace("\"url\":", "").replace("\"", "").replace("\\/", "/"));
                                    startActivity(intent);
                                }
                            });
                           // }
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        thread.start();
    }

}
