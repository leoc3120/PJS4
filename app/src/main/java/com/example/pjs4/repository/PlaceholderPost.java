package com.example.pjs4.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class PlaceholderPost {

    public void connexion(){
        OkHttpClient client = new OkHttpClient();

        Thread thread = new Thread(new Runnable() {

            Request request = new Request.Builder()
                    .url("https://edamam-recipe-search.p.rapidapi.com/search?q=chicken")
                    .get()
                    .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
                    .addHeader("X-RapidAPI-Key", "7eb892f52bmsh3efccf80bdf39f1p1c2708jsn5628d01eca98")
                    .build();


            @Override
            public void run() {
                try  {
                    Response response = null;
                    response = client.newCall(request).execute();
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    JsonParser jp = new JsonParser();
                    JsonElement je = jp.parse(response.body().string());
                    String prettyJsonString = gson.toJson(je);
                    Log.d("app", prettyJsonString);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }

}
