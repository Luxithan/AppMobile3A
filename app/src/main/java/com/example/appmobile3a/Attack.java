package com.example.appmobile3a;

import java.util.List;

public class Attack {
    private List<Cost> cost;
    private String name;
    private String text;
    private String damage;
    private Integer convertedEnergyCost;

    public List<Cost> getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getDamage() {
        return damage;
    }

    public Integer getConvertedEnergyCost() {
        return convertedEnergyCost;
    }
}
