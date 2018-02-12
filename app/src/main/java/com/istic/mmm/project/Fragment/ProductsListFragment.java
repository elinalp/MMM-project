package com.istic.mmm.project.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.istic.mmm.project.Class.Nutrient;
import com.istic.mmm.project.Class.Product;
import com.istic.mmm.project.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListFragment extends Fragment {

    @BindView(R.id.recyclerViewProducts)
    RecyclerView rvProducts;

    public ProductsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        // TODO : Get data from firebase

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

        ArrayList<Product> productArrayList = new ArrayList<>();
        productArrayList.add(p);
        productArrayList.add(p);
        productArrayList.add(p);
        productArrayList.add(p);
        productArrayList.add(p);

        rv.setAdapter(new RvProductsAdapter(productArrayList));
        return rv;
    }

    /**
     * RecyclerView.Adapter for the RecyclerViewProducts
     */
    public class RvProductsAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
        private ArrayList<Product> dataSource;

        public RvProductsAdapter(ArrayList<Product> data){
            dataSource = data;
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
            // mCallback.onArticleSelected(getAdapterPosition(), strings[getAdapterPosition()]);
            Toast.makeText(getActivity(), "Item click", Toast.LENGTH_LONG).show();
        }

        public SimpleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
    }
}
