package com.fwtai.entity;

import javax.persistence.Table;

@Table
public class City{

    private Long id;

    private String cityName;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getCityName(){
        return cityName;
    }

    public void setCityName(String cityName){
        this.cityName = cityName;
    }
}