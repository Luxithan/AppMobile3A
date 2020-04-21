package com.example.appmobile3a;

import java.util.List;

public class Card {
    private String id;
    private String name;
    private Integer nationalPokedexNumber;
    private String imageUrl;
    private String imageUrlHiRes;
    private List<Type> types;
    private String supertype;
    private String subtype;
    private String hp;          //A changer en Integer si besoin
    private List<Retreat> retreatCost;
    private Integer convertedRetreatCost;
    private String number;
    private String artist;
    private String rarity;
    private String series;
    private String set;
    private String setCode;
    private List<Attack> attacks;
    private List<Resistance> resistances;
    private List<Weakness> weaknesses;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getNationalPokedexNumber() {
        return nationalPokedexNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageUrlHiRes() {
        return imageUrlHiRes;
    }

    public List<Type> getTypes() {
        return types;
    }

    public String getSupertype() {
        return supertype;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getHp() {
        return hp;
    }

    public List<Retreat> getRetreatCost() {
        return retreatCost;
    }

    public Integer getConvertedRetreatCost() {
        return convertedRetreatCost;
    }

    public String getNumber() {
        return number;
    }

    public String getArtist() {
        return artist;
    }

    public String getRarity() {
        return rarity;
    }

    public String getSeries() {
        return series;
    }

    public String getSet() {
        return set;
    }

    public String getSetCode() {
        return setCode;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public List<Resistance> getResistances() {
        return resistances;
    }

    public List<Weakness> getWeaknesses() {
        return weaknesses;
    }
}
