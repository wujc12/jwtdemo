package com.jinchao.jwtdemo.service.Impl;

import com.jinchao.jwtdemo.dao.CityDao;
import com.jinchao.jwtdemo.domain.City;
import com.jinchao.jwtdemo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    private CityDao cityDao;

    public CityServiceImpl(@Autowired CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public City findById(String id) {
        return cityDao.findById(id).orElse(null);
    }

    @Override
    public City findByCityName(String cityName) {
        return cityDao.findByCityName(cityName);
    }

    @Override
    public boolean saveCity(City city) {
        try {
            cityDao.save(city);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteCity(City city) {
        return false;
    }
}
