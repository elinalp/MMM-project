package com.istic.mmm.project;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Pierre-Louis on 12/02/2018.
 */

public class Requester {
    public void sendGetRequest(String barreCode){
        // Instantiate the RequestQueue.
        //RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://fr.openfoodfacts.org/api/v0/produit/3029330003533.json";

        // Request a string response from the provided URL.
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("Response: " + response.get("code"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        System.out.println("Error");
                    }
                });
        // Add the request to the RequestQueue.
        //queue.add(jsObjRequest);
    }
}
