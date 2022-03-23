package com.example.pjs4.repository;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class PlaceholderPost {

    public String connexion(){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://edamam-recipe-search.p.rapidapi.com/search")
                .get()
                .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "54e4bb803cmshb6228af55f580c7p1e45f1jsn6123d794c6bc")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

}
