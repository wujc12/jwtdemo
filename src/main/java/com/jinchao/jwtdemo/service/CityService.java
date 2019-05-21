package com.jinchao.jwtdemo.service;

import com.jinchao.jwtdemo.domain.City;

public interface CityService {
    City findById(String id);

    City findByCityName(String cityName);

    boolean saveCity(City city);

    boolean deleteCity(City city);
}
