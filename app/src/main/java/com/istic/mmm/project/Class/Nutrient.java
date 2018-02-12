package com.istic.mmm.project.Class;

import java.util.ArrayList;

/**
 * Created by Elina on 12/02/2018.
 */

public class Nutrient {

    private String nutriscoreGrade;
    private ArrayList<NutrientDetails> landmark;

    public Nutrient() {}

    public String getNutriscoreGrade() {
        return nutriscoreGrade;
    }

    public void setNutriscoreGrade(String nutriscoreGrade) {
        this.nutriscoreGrade = nutriscoreGrade;
    }

    public ArrayList<NutrientDetails> getLandmark() {
        return landmark;
    }

    public void setLandmark(ArrayList<NutrientDetails> landmark) {
        this.landmark = landmark;
    }
}
