package com.istic.mmm.project.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.istic.mmm.project.Class.Nutrient;
import com.istic.mmm.project.Class.Product;
import com.istic.mmm.project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsFragment extends Fragment {
    private Product product;
    @BindView(R.id.detailPicture) ImageView picture;
    @BindView(R.id.detailName) TextView name;
    @BindView(R.id.detailDescription) TextView description;
    @BindView(R.id.detailQuantity) TextView quantity;
    @BindView(R.id.detailNutriscore) TextView nutriscore;
    @BindView(R.id.rvSimilarProducts) RecyclerView rvSimilarProducts;

    private OnFragmentInteractionListener mListener;

    public DetailsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = getArguments().getParcelable("product");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        if(product != null){
            Picasso.with(picture.getContext()).load(product.getImageUrl()).into(picture);
            name.setText(product.getName() + " - " + product.getBrand());
            description.setText(product.getIngredientsText());
            quantity.setText(product.getQuantity());
            nutriscore.setText(product.getNutriscoreGrade().toUpperCase());

            // TODO : Add nutrients to details
//            for(Nutrient nutrient : product.getNutrients()){
//                switch (nutrient.getName()){
//                    case "sugars" :
//                        sugarLevel.setText(nutrient.getName()+" : "+nutrient.getLevel());
//                        break;
//                    case "salt" :
//                        saltLevel.setText(nutrient.getName()+" : "+nutrient.getLevel());
//                        break;
//                    case "fat" :
//                        fatLevel.setText(nutrient.getName()+" : "+nutrient.getLevel());
//                        break;
//                    case "saturated-fat":
//                        saturatedFatLevel.setText(nutrient.getName()+" : "+nutrient.getLevel());
//                        break;
//                }
//            }
        }

        // Recycle view similar products
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvSimilarProducts.setLayoutManager(horizontalLayoutManagaer);

        // STATIC
        ArrayList<String> similarProductPictures = new ArrayList<>();
        similarProductPictures.add("https://static.openfoodfacts.org/images/products/80176800/front_fr.38.400.jpg");
        similarProductPictures.add("https://static.openfoodfacts.org/images/products/80176800/front_fr.38.400.jpg");
        similarProductPictures.add("https://static.openfoodfacts.org/images/products/80176800/front_fr.38.400.jpg");
        similarProductPictures.add("https://static.openfoodfacts.org/images/products/80176800/front_fr.38.400.jpg");
        similarProductPictures.add("https://static.openfoodfacts.org/images/products/80176800/front_fr.38.400.jpg");

        ArrayList<String> similarProductNames = new ArrayList<>();
        similarProductNames.add("Nutella");
        similarProductNames.add("Nutella");
        similarProductNames.add("Nutella");
        similarProductNames.add("Nutella");
        similarProductNames.add("Nutella");

        RvSimilarProductAdapter adapter = new RvSimilarProductAdapter(similarProductPictures, similarProductNames);
        rvSimilarProducts.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: localisation
        void onFragmentInteraction(Uri uri);
    }

    /**
     * RecyclerView.Adapter for the RecyclerViewProducts
     */
    public class RvSimilarProductAdapter extends RecyclerView.Adapter<SimilarProductViewHolder> {
        private ArrayList<String> photos;
        private ArrayList<String> names;

        public RvSimilarProductAdapter(ArrayList<String> photos, ArrayList<String> name ){
            this.photos = photos;
            this.names = name;
        }

        @Override
        public SimilarProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_similar_product, parent, false);

            return new SimilarProductViewHolder(v);
        }

        @Override
        public void onBindViewHolder(SimilarProductViewHolder holder, int position) {
            holder.productNameField.setText(this.names.get(position));
            Picasso.with(holder.imageSimilarProduct.getContext()).load(this.photos.get(position)).into(holder.imageSimilarProduct);
        }

        @Override
        public int getItemCount() {
            return photos.size();
        }
    }

    /**
     * ViewHolder for the RecyclerViewProducts
     */
    public class SimilarProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imageSimilarProduct)
        ImageView imageSimilarProduct;
        @BindView(R.id.productNameField)
        TextView productNameField;

        @Override
        public void onClick(View v) {}

        public SimilarProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
    }
}
