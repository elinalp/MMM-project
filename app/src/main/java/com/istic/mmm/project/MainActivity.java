package com.istic.mmm.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istic.mmm.project.Class.Nutrient;
import com.istic.mmm.project.Class.Product;
import com.istic.mmm.project.Fragment.DetailsFragment;
import com.istic.mmm.project.Fragment.ProductsListFragment;
import com.istic.mmm.project.Fragment.ScanFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener,
                    ProductsListFragment.OnFragmentProductsListener,
                    DetailsFragment.OnDetailsInteractionListener,
                    ScanFragment.OnScanListener{

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;

    private DetailsFragment detailsFragment;
    private ProductsListFragment productsListFragment;
    private ScanFragment scanFragment;

    @Override
    public void onProductSelected(Product product){

        // TODO : Fix parcelable
        product.setStores(new ArrayList<String>());
        product.setNutrients(new ArrayList<Nutrient>());

        if (findViewById(R.id.frame_main_product_details) == null) {
            Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
            intent.putExtra("product", product);
            startActivity(intent);
        } else {
            Bundle bundleProduct = new Bundle();
            bundleProduct.putParcelable("product", product);
            detailsFragment.setArguments(bundleProduct);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_main_product_details, this.detailsFragment).commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Firebase instance
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Checked if user is already connected
        if (mFirebaseAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), LogInActivity.class));
            finish();
        }

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Handle default fragment
        intanciateDefaultFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // Tablet Mode
        if(this.detailsFragment != null){
            getSupportFragmentManager().beginTransaction().remove(this.detailsFragment).commit();
        }

        if (id == R.id.nav_scan) {
            // Handle scan Fragment
            this.scanFragment = new ScanFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_list, this.scanFragment).commit();
        } else if (id == R.id.nav_product_list) {
            // Handle the Products List Fragment and Details Fragment
            intanciateDefaultFragment();
        } else if (id == R.id.nav_logout) {
            // Start LoginActivity
            mFirebaseAuth.signOut();
            startActivity(new Intent(getApplicationContext(), LogInActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void intanciateDefaultFragment(){
        // Handle the Products List Fragment and Details Fragment
        if (findViewById(R.id.frame_main_product_details) != null) {
            detailsFragment = new DetailsFragment();;
        }
        this.productsListFragment = new ProductsListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_list, this.productsListFragment).commit();
    }

    @Override
    public void onScan(String barCode) {
        Intent intent = new Intent(getApplicationContext(),AddActivity.class);
        intent.putExtra("barCode", barCode);
        startActivity(intent);
    }

    @Override
    public void onClickLocation() {
        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
        finish();
    }
}
