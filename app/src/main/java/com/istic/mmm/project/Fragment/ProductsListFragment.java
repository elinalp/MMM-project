package com.istic.mmm.project.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istic.mmm.project.Class.Product;
import com.istic.mmm.project.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListFragment extends Fragment {

    @BindView(R.id.recyclerViewProducts)
    RecyclerView rvProducts;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;

    private ArrayList<Product> products = new ArrayList<>();

    private OnFragmentProductsListener productListener;

    public ProductsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get Firebase instance
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserId = mFirebaseUser.getUid();

        final RvProductsAdapter adapter = new RvProductsAdapter();

        // Use Firebase to populate the list.
        mDatabase.child("users").child(mUserId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Product product = ds.getValue(Product.class);
                    products.add(product);
                }
                adapter.setData(products);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        rv.setAdapter(adapter);
        return rv;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentProductsListener) {
            productListener = (OnFragmentProductsListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentProductsListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        productListener = null;
    }

    /**
     * RecyclerView.Adapter for the RecyclerViewProducts
     */
    public class RvProductsAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
        private ArrayList<Product> dataSource;

        public RvProductsAdapter(){
            dataSource = new ArrayList<Product>();
        }

        public void setData(ArrayList<Product> products){
            dataSource = products;
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product, parent, false);

            return new SimpleViewHolder(v);
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            holder.productNameField.setText(dataSource.get(position).getName());
            holder.productBrandField.setText(dataSource.get(position).getBrand());
        }

        @Override
        public int getItemCount() {
            return dataSource.size();
        }
    }

    /**
     * ViewHolder for the RecyclerViewProducts
     */
    public class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.productNameField)
        TextView productNameField;
        @BindView(R.id.productBrandField)
        TextView productBrandField;

        @Override
        public void onClick(View v) {
            // Send the event to the host activity
            productListener.onProductSelected(products.get(getAdapterPosition()));
        }

        public SimpleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     */
    public interface OnFragmentProductsListener {
        void onProductSelected(Product product);
    }
}
