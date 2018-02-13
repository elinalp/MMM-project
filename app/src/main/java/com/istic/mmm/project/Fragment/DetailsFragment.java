package com.istic.mmm.project.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.istic.mmm.project.Class.Nutrient;
import com.istic.mmm.project.Class.Product;
import com.istic.mmm.project.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsFragment extends Fragment {
    private Product product;
    @BindView(R.id.textName) TextView name;
    @BindView(R.id.textBarCode) TextView barCode;
    @BindView(R.id.textNutriscore) TextView nutriscore;
    @BindView(R.id.textSaltLevel) TextView saltLevel;
    @BindView(R.id.textFatLevel) TextView fatLevel;
    @BindView(R.id.textSaturatedFatLevel) TextView saturatedFatLevel;
    @BindView(R.id.textSugarsLevel) TextView sugarLevel;
    @BindView(R.id.textIngredientsLabel) TextView ingredients;

    private OnFragmentInteractionListener mListener;

    public DetailsFragment() {
        // Required empty public constructor
    }

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
            name.setText(product.getName());
            barCode.setText(product.getBarCode());
            nutriscore.setText(product.getNutriscoreGrade());
            for(Nutrient nutrient : product.getNutrients()){
                switch (nutrient.getName()){
                    case "sugars" :
                        sugarLevel.setText(nutrient.getName()+" : "+nutrient.getLevel());
                        break;
                    case "salt" :
                        saltLevel.setText(nutrient.getName()+" : "+nutrient.getLevel());
                        break;
                    case "fat" :
                        fatLevel.setText(nutrient.getName()+" : "+nutrient.getLevel());
                        break;
                    case "saturated-fat":
                        saturatedFatLevel.setText(nutrient.getName()+" : "+nutrient.getLevel());
                        break;
                }
            }
            ingredients.setText(product.getIngredientsText());
        }
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
}
