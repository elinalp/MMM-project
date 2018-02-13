package com.istic.mmm.project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istic.mmm.project.Class.MySingleton;
import com.istic.mmm.project.Class.Nutrient;
import com.istic.mmm.project.Class.Product;
import com.istic.mmm.project.Fragment.DetailsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddActivity extends AppCompatActivity implements DetailsFragment.OnFragmentInteractionListener {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private final String url = "http://fr.openfoodfacts.org/api/v0/produit/";
    DetailsFragment detailsFragment;
    Product myproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        // Get Firebase instance
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        detailsFragment = new DetailsFragment();

        Bundle bundle = getIntent().getExtras();
        String barCode = bundle.getString("barCode");
        //String barCode = "3268840001008";
        sendRequest(barCode);


    }

    public void sendRequest(String barCode){
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url+barCode, null, new Response.Listener<JSONObject>() {

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
                        nutrientsList.add(nutrient);
                    }

                    JSONArray stores = productJson.getJSONArray("stores_tags");
                    for (int y = 0; y < stores.length(); y++) {
                        String store = (String) stores.get(y);
                        storesList.add(store);
                    }

                    myproduct = new Product(barCode);
                    myproduct.setName(productName);
                    myproduct.setBrand(brand);
                    myproduct.setNutriscoreGrade(nutritionGrade);
                    myproduct.setImageUrl(imageUrl);
                    myproduct.setIngredientsText(ingredients);
                    myproduct.setQuantity(quantity);
                    myproduct.setStores(storesList);
                    myproduct.setNutrients(nutrientsList);

                    Bundle bundleProduct = new Bundle();
                    bundleProduct.putParcelable("product", myproduct);
                    detailsFragment.setArguments(bundleProduct);

                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.frame_details, detailsFragment)
                            .commit();

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

    public void addProduct(){
        if(myproduct != null){
            mUserId = mFirebaseUser.getUid();
            mDatabase.child("users").child(mUserId).child("products").push().setValue(myproduct);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @OnClick(R.id.addButton) void add() {
        addProduct();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @OnClick(R.id.cancelButton) void cancel() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
