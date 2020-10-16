package com.demo.volleydemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final XBannerView bannerView = findViewById(R.id.banner_view);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new JsonObjectRequest(
                Request.Method.GET,
                "https://dog.ceo/api/breed/labrador/images",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = (JSONArray) response.get("message");
                            ArrayList<String> links = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                links.add(array.getString(i));
                            }
                            bannerView.fillData(links);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("net error", Objects.requireNonNull(error.getMessage()));
            }
        }));
    }
}