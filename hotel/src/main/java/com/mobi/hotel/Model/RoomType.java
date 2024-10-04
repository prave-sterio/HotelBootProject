package com.mobi.hotel.Model;


public enum RoomType {

    PREMIUM("premium" , 2000),
    STANDARD("standard" , 1500),
    MINI("mini" , 1000);

    String type;
    int price;

    RoomType(String type , int price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }
}

