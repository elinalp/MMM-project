package com.istic.mmm.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istic.mmm.project.Class.Nutrient;
import com.istic.mmm.project.Class.Product;
import com.istic.mmm.project.Fragment.ProductsListFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;

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

        addProduct();
    }

    public void addProduct(){
        mUserId = mFirebaseUser.getUid();

        // Created Nutrient
        Nutrient nutrient = new Nutrient();
        nutrient.setLevel("hight");
        nutrient.setName("Salt");
        nutrient.setQuantity("3%");

        ArrayList<Nutrient> nutrients = new ArrayList<>();
        nutrients.add(nutrient);

        // Created Product
        Product p = new Product("354444");
        p.setName("Nutella");
        p.setBrand("Ferrero");
        p.setImageUrl("htttp");
        p.setQuantity("3g");
        p.setIngredientsText("salt, fruit");
        p.setStores("carrefour");
        p.setNutriscoreGrade("A");
        p.setNutrients(nutrients);

        mDatabase.child("users").child(mUserId).child("products").push().setValue(p);
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

        if (id == R.id.nav_scan) {
            // Handle the scan Fragment
            Intent scanIntent = new Intent();
            startActivity(new Intent(getApplicationContext(), ScanActivity.class));

        } else if (id == R.id.nav_product_list) {
            // Handle the scan ProductListFragment
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.add(R.id.frame_list, new ProductsListFragment()).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_list, new ProductsListFragment()).commit();
            Toast.makeText(this, "List fragment", Toast.LENGTH_LONG).show();



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
}
