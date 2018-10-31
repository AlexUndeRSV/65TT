package com.lynx.testtask65apps.domain.dataclass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Response implements Parcelable {
    @SerializedName("f_name")
    @Expose
    private String fName;
    @SerializedName("l_name")
    @Expose
    private String lName;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("avatr_url")
    @Expose
    private String avatrUrl;
    @SerializedName("specialty")
    @Expose
    private ArrayList<Speciality> specialty = null;

    public Response(){}

    protected Response(Parcel in) {
        fName = in.readString();
        lName = in.readString();
        birthday = in.readString();
        avatrUrl = in.readString();
        List<Speciality> specialityList = new ArrayList<>();
        in.readTypedList(specialityList, Speciality.CREATOR);
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fName);
        dest.writeString(lName);
        dest.writeString(birthday);
        dest.writeString(avatrUrl);
        dest.writeList(specialty);
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatarUrl() {
        return avatrUrl;
    }

    public void setAvatrUrl(String avatrUrl) {
        this.avatrUrl = avatrUrl;
    }

    public ArrayList<Speciality> getSpecialty() {
        return specialty;
    }

    public void setSpecialty(ArrayList<Speciality> specialty) {
        this.specialty = specialty;
    }

}
