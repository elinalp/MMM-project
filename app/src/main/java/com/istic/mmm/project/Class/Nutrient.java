package com.istic.mmm.project.Class;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Elina on 12/02/2018.
 */

public class Nutrient implements Parcelable {

    private String name;
    private String level;
    private String quantity;

    public Nutrient() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(level);
        dest.writeString(quantity);
    }

    public static final Parcelable.Creator<Nutrient> CREATOR = new Parcelable.Creator<Nutrient>() {
        @Override
        public Nutrient createFromParcel(Parcel source) {
            return new Nutrient(source);
        }

        @Override
        public Nutrient[] newArray(int size) {
            return new Nutrient[size];
        }
    };

    public Nutrient(Parcel in) {
        this.name = in.readString();
        this.level = in.readString();
        this.quantity = in.readString();
    }
}
