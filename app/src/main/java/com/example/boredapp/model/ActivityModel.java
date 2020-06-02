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

    public String getActivity() {
        return mActivity;
    }

    public void setActivity(String activity) {
        mActivity = activity;
    }

    public double getAccessibility() {
        return mAccessibility;
    }

    public void setAccessibility(double accessibility) {
        mAccessibility = accessibility;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public int getParticipants() {
        return mParticipants;
    }

    public void setParticipants(int participants) {
        mParticipants = participants;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        this.mLink = link;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }
}
