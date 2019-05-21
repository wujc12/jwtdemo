package com.jinchao.jwtdemo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "city")
public class City {
    @Id
    private String id;

    @Field("city_name")
    private String cityName;

    @Field("city_area")
    private double cityArea;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getCityArea() {
        return cityArea;
    }

    public void setCityArea(double cityArea) {
        this.cityArea = cityArea;
    }
}
