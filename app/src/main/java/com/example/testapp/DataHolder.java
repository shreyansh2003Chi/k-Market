package com.example.testapp;

public class DataHolder {
    String catagories,title,price,pimage,currentTime;

    public DataHolder(String catagories, String title, String price, String pimage, String currentTime) {
        this.catagories = catagories;
        this.title = title;
        this.price = price;
        this.pimage = pimage;
        this.currentTime=currentTime;
    }

    public String getCatagories() {
        return catagories;
    }

    public void setCatagories(String catagories) {
        this.catagories = catagories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
