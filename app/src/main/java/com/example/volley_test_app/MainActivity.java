package com.example.volley_test_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

//    RequestQueue mRequestQueue; // create a request queue for the json requests
    RequestQueue mSingleRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mRequestQueue = Volley.newRequestQueue(this); // This will initialize the queue
        mSingleRequestQueue = VolleySingleton.getInstance().getRequestQueue(); // This is the singleton's way of getting the request queue

        // This will work for an actual json object, but NOT an array
        JsonObjectRequest myRequest = new JsonObjectRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/users/1", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Successfully retrieved JSON");
                Log.i("RESPONSE VALID DATA", "We did it!" + response);
                System.out.println("My response is: " + response);
                try {
                    Log.i("OBJINFO", "Id is: " + response.getInt("id"));
                    Log.i("OBJINFO", "Name is: " + response.getString("name"));
                    int userId = response.getInt("id");
                    Toast.makeText(getApplicationContext(), "User id is: " + userId, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Your internet went out", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error no json found");
                Log.e("RESPONSE ERROR", error.getMessage());
            }
        });

        mSingleRequestQueue.add(myRequest);


        // This will work to retrieve JSON arrays
        JsonArrayRequest myArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/users", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("ARRAYRESPONSE", response + "");
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Log.i("JSONINFO", "id is: " + response.getJSONObject(i).getInt("id"));
                        Log.i("JSONINFO", "name is: " + response.getJSONObject(i).getString("name"));
                        Log.i("JSONINFO", "username is: " + response.getJSONObject(i).getString("username"));
                        Log.i("JSONINFO", "email is: " + response.getJSONObject(i).getString("email"));
                        Log.i("JSONINFO", "address is: " + response.getJSONObject(i).getString("address"));
                        Log.i("JSONINFO", "phone is: " + response.getJSONObject(i).getString("phone"));
                        Log.i("JSONINFO", "website is: " + response.getJSONObject(i).getString("website"));
                        Log.i("JSONINFO", "company is: " + response.getJSONObject(i).getString("company"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ARRAYERROR", error.getMessage());
            }
        });

        mSingleRequestQueue.add(myArrayRequest);
    }
}