package com.example.compliance_king;

public class ItemUseProduct {
    int img;
    String name;
    String date1;
    String date2;

    public ItemUseProduct(int img, String name, String date1, String date2) {
        this.img = img;
        this.name = name;
        this.date1 = date1;
        this.date2 = date2;
    }

    public int getImg() { return this.img;  }

    public String getName() { return this.name; }

    public String getDate1() {
        return this.date1;
    }

    public String getDate2() {
        return this.date2;
    }

    public void setImg(int img) { this.img = img; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }
}
