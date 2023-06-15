package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Excursion implements Parcelable {
    private String name, type;
    private ArrayList<Landmark> landmarks;
    private int price, desc;



    public Excursion(String name, String type, int price, int desc){
        this.name = name;
        this.landmarks = new ArrayList<>();
        this.price = price;
        this.type = type;
        this.desc = desc;
    }

    protected Excursion(Parcel in) {
        name = in.readString();
        type = in.readString();
        landmarks = in.createTypedArrayList(Landmark.CREATOR);
        price = in.readInt();
        desc = in.readInt();
    }

    public static final Creator<Excursion> CREATOR = new Creator<Excursion>() {
        @Override
        public Excursion createFromParcel(Parcel in) {
            return new Excursion(in);
        }

        @Override
        public Excursion[] newArray(int size) {
            return new Excursion[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getType(){
        return type;
    }
    public int getDesc() {
        return desc;
    }

    public ArrayList<Landmark> getLandmarks(){
        return landmarks;
    }

    public int getPrice() {
        return price;
    }

    public void addLandmark(Landmark landmark){ landmarks.add(landmark);}
    @Override
    public String toString(){
        return getName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeTypedList(landmarks);
        dest.writeInt(price);
        dest.writeInt(desc);
    }
}
