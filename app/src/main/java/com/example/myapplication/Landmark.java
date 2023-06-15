package com.example.myapplication;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.yandex.mapkit.geometry.Point;

public class Landmark implements Parcelable {
    private String name;
    private int desc;
    private String time;
    private Point point;
    private String type;

    public Landmark(String name, int desc, String time, Point point, String type){
        this.name = name;
        this.desc = desc;
        this.time = time;
        this.point = point;
        this.type = type;
    }

    protected Landmark(Parcel in) {
        name = in.readString();
        desc = in.readInt();
        time = in.readString();
        type = in.readString();
        point = new Point(in.readDouble(),in.readDouble());
    }

    public static final Creator<Landmark> CREATOR = new Creator<Landmark>() {
        @Override
        public Landmark createFromParcel(Parcel in) {
            return new Landmark(in);
        }

        @Override
        public Landmark[] newArray(int size) {
            return new Landmark[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getDesc() {
        return desc;
    }

    public Point getPoint() {
        return point;
    }

    public String getTime() {
        return time;
    }

    public String getType(){return type;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString(){
        return getName();
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(desc);
        dest.writeString(time);
        dest.writeString(type);
        dest.writeDouble(point.getLatitude());
        dest.writeDouble(point.getLongitude());
    }
}
