package com.example.myapplication;

public class kijijiData {
    private String address1;

    public kijijiData(String address1, String address2, String image) {
        this.address1 = address1;
        this.address2 = address2;
        this.image = image;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public kijijiData() {
    }

    private String address2;
    private String image;
}
