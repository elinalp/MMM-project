package com.istic.mmm.project;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.istic.mmm.project.Class.Nutrient;
import com.istic.mmm.project.Class.Product;
import com.istic.mmm.project.Fragment.DetailsFragment;
import com.istic.mmm.project.Fragment.ProductsListFragment;

import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity implements DetailsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Product product = getIntent().getExtras().getParcelable("product");

        // Refactor
        ArrayList<String> store = new ArrayList<>();
        ArrayList<Nutrient> nutrients = new ArrayList<>();
        product.setStores(store);
        product.setNutrients(nutrients);

        Bundle bundleProduct = new Bundle();
        bundleProduct.putParcelable("product", product);
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundleProduct);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_product_details, detailsFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
