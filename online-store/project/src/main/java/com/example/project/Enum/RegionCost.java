package com.example.project.Enum;

public enum RegionCost {
    CHUY(300),
    TALAS(500),
    ISSYKKOL(450),
    NARYN(650),
    JALALABAD(700),
    OSH(1000),
    BATKEN(2000);

    private final Integer price;

    RegionCost(Integer i) {
        this.price = i;
    }

    public Integer getPrice() {
        return price;
    }
}
