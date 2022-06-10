package com.example.onlineshop.utils;


import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = 6297385302078200511L;

    private String name;
    private String email;
    private int id;
    private String country;

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    private Integer money;

    public User(String name, String email,  String country,int id, Integer money) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.country = country;
        this.money = money;
    }

    public User(String nm, String em, String country, int i){
        this.name=nm;
        this.id=i;
        this.country=country;
        this.email=em;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString(){
        return "Name="+this.name+", Email="+this.email+", Country="+this.country;
    }
}

