package com.quancuteexample.officeworkout;

import android.os.Parcel;
import android.os.Parcelable;

public class WorkoutExcercise implements Parcelable{
    private String name;
    private String rep;
    private String gifName;
    public WorkoutExcercise(String name,String rep,String gifName){
        this.name=name;
        this.rep=rep;
        this.gifName=gifName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getGifName(){ return gifName;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(rep);
        dest.writeString(gifName);
    }
    public static final Parcelable.Creator<WorkoutExcercise> CREATOR
            = new Parcelable.Creator<WorkoutExcercise>() {
        public WorkoutExcercise createFromParcel(Parcel in) {
            return new WorkoutExcercise(in);
        }

        public WorkoutExcercise[] newArray(int size) {
            return new WorkoutExcercise[size];
        }
    };

    private WorkoutExcercise(Parcel in) {
        name=in.readString();
        rep=in.readString();
        gifName=in.readString();
    }
}
