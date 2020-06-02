package com.example.boredapp.model;

import com.google.gson.annotations.SerializedName;

public class ActivityModel {

    /**
     * activity : Learn Express.js
     * accessibility : 0.25
     * type : education
     * participants : 1
     * price : 0.1
     * link : https://expressjs.com/
     * key : 3943506
     */

    @SerializedName("activity")
    private String mActivity;
    @SerializedName("accessibility")
    private double mAccessibility;
    @SerializedName("type")
    private String mType;
    @SerializedName("participants")
    private int mParticipants;
    @SerializedName("price")
    private double mPrice;
    @SerializedName("link")
    private String mLink;
    @SerializedName("key")
    private String mId;

    public String getMActivity() {
        return mActivity;
    }

    public void setMActivity(String mActivity) {
        this.mActivity = mActivity;
    }

    public double getMAccessibility() {
        return mAccessibility;
    }

    public void setMAccessibility(double mAccessibility) {
        this.mAccessibility = mAccessibility;
    }

    public String getMType() {
        return mType;
    }

    public void setMType(String mType) {
        this.mType = mType;
    }

    public int getMParticipants() {
        return mParticipants;
    }

    public void setMParticipants(int mParticipants) {
        this.mParticipants = mParticipants;
    }

    public double getMPrice() {
        return mPrice;
    }

    public void setMPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public String getMLink() {
        return mLink;
    }

    public void setMLink(String mLink) {
        this.mLink = mLink;
    }

    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }
}
