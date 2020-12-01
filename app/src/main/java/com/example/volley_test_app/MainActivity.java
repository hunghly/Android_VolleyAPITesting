package com.example.volley_test_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue mRequestQueue; // create a request queue for the json requests

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = Volley.newRequestQueue(this); // This will initialize the queue

        // This will work for an actual json object, but NOT an array
        JsonObjectRequest myRequest = new JsonObjectRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/users/1", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Successfully retrieved JSON");
                Log.i("RESPONSE VALID DATA", "We did it!" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error no json found");
                Log.e("RESPONSE ERROR", error.getMessage());
            }
        });

        mRequestQueue.add(myRequest);


        // This will work to retrieve JSON arrays
        JsonArrayRequest myArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/users", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("ARRAYRESPONSE", response + "");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ARRAYERROR", error.getMessage());
            }
        });

        mRequestQueue.add(myArrayRequest);
    }
}