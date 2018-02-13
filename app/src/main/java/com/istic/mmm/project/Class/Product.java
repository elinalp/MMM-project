package com.istic.mmm.project.Class;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Elina on 12/02/2018.
 */

public class Product implements Parcelable {

    private String barCode;
    private String name;
    private String brand;
    private String quantity;
    private String imageUrl;
    private String ingredientsText;
    private String nutriscoreGrade;

    private ArrayList<String> stores;
    private ArrayList<Nutrient> nutrients;

    public Product() {}

    public Product(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIngredientsText() {
        return ingredientsText;
    }

    public void setIngredientsText(String ingredientsText) {
        this.ingredientsText = ingredientsText;
    }

    public ArrayList<String> getStores() {
        return stores;
    }

    public void setStores(ArrayList<String> stores) {
        this.stores = stores;
    }

    public String getNutriscoreGrade() {
        return nutriscoreGrade;
    }

    public void setNutriscoreGrade(String nutriscoreGrade) {
        this.nutriscoreGrade = nutriscoreGrade;
    }

    public ArrayList<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(ArrayList<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(barCode);
        dest.writeString(name);
        dest.writeString(brand);
        dest.writeString(quantity);
        dest.writeString(imageUrl);
        dest.writeString(ingredientsText);
        dest.writeStringList(stores);
//        dest.writeString(nutriscoreGrade);
//        dest.writeTypedList(nutrients);
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public Product(Parcel in) {
        this.barCode = in.readString();
        this.name = in.readString();
        this.brand = in.readString();
        this.quantity = in.readString();
        this.imageUrl = in.readString();
        this.ingredientsText = in.readString();
        this.nutriscoreGrade = in.readString();
//        in.readStringList(this.stores);
//        in.readTypedList(this.nutrients, Nutrient.CREATOR);
    }

    @Override
    public String toString() {
        return "Product{" +
                "barCode='" + barCode + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", quantity='" + quantity + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", ingredientsText='" + ingredientsText + '\'' +
                ", nutriscoreGrade='" + nutriscoreGrade + '\'' +
                '}';
    }
}
