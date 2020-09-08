package com.example.business_todo;

import android.os.Parcel;
import android.os.Parcelable;


//implements Parcelable methodunu en son yapacağız Intent arası bilgi transferi için  bilgileri(Listview i bitirdikten sonra)


public class Posts implements Parcelable {
    private String userId;
   private String userEmail;
    private String userEventName;
    private String userRepresentName;
    private  String userRepresentNumber;
    private  String userEventDate;
    private   String userEventPeople;

    public Posts() {
    }

    public Posts(String userId, String userEmail, String userEventName, String userRepresentName, String userRepresentNumber, String userEventDate, String userEventPeople) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userEventName = userEventName;
        this.userRepresentName = userRepresentName;
        this.userRepresentNumber = userRepresentNumber;
        this.userEventDate = userEventDate;
        this.userEventPeople = userEventPeople;
    }

    protected Posts(Parcel in) {
        userId = in.readString();
        userEmail = in.readString();
        userEventName = in.readString();
        userRepresentName = in.readString();
        userRepresentNumber = in.readString();
        userEventDate = in.readString();
        userEventPeople = in.readString();
    }

    public static final Creator<Posts> CREATOR = new Creator<Posts>() {
        @Override
        public Posts createFromParcel(Parcel in) {
            return new Posts(in);
        }

        @Override
        public Posts[] newArray(int size) {
            return new Posts[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEventName() {
        return userEventName;
    }

    public void setUserEventName(String userEventName) {
        this.userEventName = userEventName;
    }

    public String getUserRepresentName() {
        return userRepresentName;
    }

    public void setUserRepresentName(String userRepresentName) {
        this.userRepresentName = userRepresentName;
    }

    public String getUserRepresentNumber() {
        return userRepresentNumber;
    }

    public void setUserRepresentNumber(String userRepresentNumber) {
        this.userRepresentNumber = userRepresentNumber;
    }

    public String getUserEventDate() {
        return userEventDate;
    }

    public void setUserEventDate(String userEventDate) {
        this.userEventDate = userEventDate;
    }

    public String getUserEventPeople() {
        return userEventPeople;
    }

    public void setUserEventPeople(String userEventPeople) {
        this.userEventPeople = userEventPeople;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(userEmail);
        dest.writeString(userEventName);
        dest.writeString(userRepresentName);
        dest.writeString(userRepresentNumber);
        dest.writeString(userEventDate);
        dest.writeString(userEventPeople);
    }
}
