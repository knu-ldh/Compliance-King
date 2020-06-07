package com.example.compliance_king;

public class ItemUseProduct {
    String name;
    String date1;
    String date2;

    public ItemUseProduct(String name, String date1, String date2) {
        this.name = name;
        this.date1 = date1;
        this.date2 = date2;
    }

    public String getName() {
        return name;
    }

    public String getDate1() {
        return date1;
    }

    public String getDate2() {
        return date2;
    }

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
