package com.istic.mmm.project.Class;

import java.util.ArrayList;

/**
 * Created by Elina on 12/02/2018.
 */

public class Product {

    private String barCode;
    private String name;
    private String brand;
    private String quantity;
    private String imageUrl;
    private String ingredientsText;
    private String stores;

    private ArrayList<Nutrient> nutrient;

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

    public String getStores() {
        return stores;
    }

    public void setStores(String stores) {
        this.stores = stores;
    }

    public ArrayList<Nutrient> getNutrient() {
        return nutrient;
    }

    public void setNutrient(ArrayList<Nutrient> nutrient) {
        this.nutrient = nutrient;
    }
}
