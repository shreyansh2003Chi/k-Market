package com.example.testapp;
import android.net.Uri;

public class ModelRec {
    String catagories;
    String pimage;
    String price;
    String title;
    String currentTime;
    ModelRec()
    {

    }

    public ModelRec(String catagories, String pimage, String price, String title,String currentTime) {
        this.catagories = catagories;
        this.pimage = pimage;
        this.price = price;
        this.title = title;
        this.currentTime= currentTime;
    }

    public String getCatagories() {
        return catagories;
    }

    public void setCatagories(String catagories) {
        this.catagories = catagories;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}