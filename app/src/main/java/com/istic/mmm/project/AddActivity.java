package com.istic.mmm.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.istic.mmm.project.Class.Nutrient;
import com.istic.mmm.project.Class.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Bundle bundle = getIntent().getExtras();
        //String barCode = bundle.getString("barCode");
        String barCode = "3268840001008";
        String url = "http://fr.openfoodfacts.org/api/v0/produit/" + barCode;
        sendRequest(url);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentLayout, new DetailsFragment())
                .commit();
    }

    public void sendRequest(String url){
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    String barCode = response.getString("code");
                    JSONObject productJson = response.getJSONObject("product");
                    String nutritionGrade = productJson.getString("nutrition_grade_fr");
                    String brand = productJson.getString("brands");
                    String productName = productJson.getString("generic_name_fr");
                    String imageUrl = productJson.getString("image_url");
                    String ingredients = productJson.getString("ingredients_text_fr");
                    String quantity = productJson.getString("quantity");
                    ArrayList<String> storesList = new ArrayList<>();
                    ArrayList<Nutrient> nutrientsList = new ArrayList<>();

                    JSONObject nutrients = productJson.getJSONObject("nutrient_levels");
                    String[] nutrientsName = {"sugars", "salt", "saturated-fat", "fat"};
                    for(int i = 0 ; i < nutrientsName.length ; i++){
                        String nutrientName = nutrientsName[i];
                        Nutrient nutrient = new Nutrient();
                        nutrient.setName(nutrientName);
                        nutrient.setLevel(nutrients.getString(nutrientName));
                        nutrient.setQuantity(productJson.getJSONObject("nutriments").getString(nutrientName));
                    }

                    JSONArray stores = productJson.getJSONArray("stores_tags");
                    for (int y = 0; y < stores.length(); y++) {
                        String store = (String) stores.get(y);
                        storesList.add(store);
                    }

                    Product product = new Product(barCode);
                    product.setName(productName);
                    product.setBrand(brand);
                    product.setNutriscoreGrade(nutritionGrade);
                    product.setImageUrl(imageUrl);
                    product.setIngredientsText(ingredients);
                    product.setQuantity(quantity);
                    product.setStores(storesList);
                    product.setNutrients(nutrientsList);
                    System.out.println(product.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
            }
        });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }
}
