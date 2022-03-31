package com.example.pjs4.repository;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APILoader extends AppCompatActivity {

    public void connexion(TextView label, TextView diet, ImageView img, Button button, EditText entree, Button site, TextView urlLien, Button prec){
        OkHttpClient client = new OkHttpClient();
        ArrayList<JSONObject> idList = new ArrayList<>();
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

                    label.setText(arrayy.get(1).replace("\"label\":", "").replace("\"", "").replace("\"", ""));
                    diet.setText(arrayy.get(7).replace("\"dietLabels\":[", "").replace("]", "").replace("\"", "").replace("\"", ""));

                    URL url = new URL(arrayy.get(2).replace("\"image\":\"", "").replace("\"", "").replace("\\/", "/"));
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    APILoader.this.runOnUiThread(new Runnable() {
                        public void run() {
                            img.setImageBitmap(bmp);
                        }
                    });

                    String mylink = arrayy.get(4).replace("\"url\":", "").replace("\"", "").replace("\\/", "/");
                    urlLien.setText(mylink);


                    button.setOnClickListener(new View.OnClickListener() {
                        int i = 1;
                        @Override
                        public void onClick(View view) {

                            List<String> newarrayy = Arrays.asList(idList.get(i).toString().split(","));

                            //while(i < newarrayy.size()) {
                            Log.d("val + i", String.valueOf(i));
                            Picasso.get().load(newarrayy.get(2).replace("\"image\":\"", "").replace("\"", "").replace("\\/", "/")).into(img);
                            label.setText(newarrayy.get(1).replace("\"label\":", "").replace("\"", "").replace("\"", ""));
                            diet.setText(newarrayy.get(7).replace("\"dietLabels\":[", "").replace("]", "").replace("\"", "").replace("\"", ""));
                            String mylink = newarrayy.get(4).replace("\"url\":", "").replace("\"", "").replace("\\/", "/");
                            urlLien.setText(mylink);
                            i++;
                            if (prec.isPressed()){
                                i--;
                            }

                        }

                    });

                    prec.setOnClickListener(new View.OnClickListener() {
                        int i = 1;
                        @Override
                        public void onClick(View view) {
                            List<String> newarrayy = Arrays.asList(idList.get(i).toString().split(","));

                            //while(i < newarrayy.size()) {
                            Log.d("val + i", String.valueOf(i));
                            Picasso.get().load(newarrayy.get(2).replace("\"image\":\"", "").replace("\"", "").replace("\\/", "/")).into(img);
                            label.setText(newarrayy.get(1).replace("\"label\":", "").replace("\"", "").replace("\"", ""));
                            diet.setText(newarrayy.get(7).replace("\"dietLabels\":[", "").replace("]", "").replace("\"", "").replace("\"", ""));
                            String mylink = newarrayy.get(4).replace("\"url\":", "").replace("\"", "").replace("\\/", "/");
                            urlLien.setText(mylink);
                            i--;
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
