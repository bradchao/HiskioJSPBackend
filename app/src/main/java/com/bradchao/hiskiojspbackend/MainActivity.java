package com.bradchao.hiskiojspbackend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
    }

    public void go(View view) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://192.168.50.208:8080/HiskioServer/hiskio01.jsp",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.v("bradlog", response);
                        parseJSON(response.trim());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("bradlog", error.toString());
                    }
                }
        );
        queue.add(request);
    }

    private void parseJSON(String json){
        try{
            JSONArray root = new JSONArray(json);
            for (int i=0; i<root.length(); i++){
                JSONObject row = root.getJSONObject(i);
                Log.v("bradlog", row.getString("name"));
            }
        }catch (Exception e){
            Log.v("bradlog", e.toString());
        }
    }
}