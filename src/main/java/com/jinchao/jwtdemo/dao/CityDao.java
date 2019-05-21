package com.jinchao.jwtdemo.dao;

import com.jinchao.jwtdemo.domain.City;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CityDao extends MongoRepository<City, String> {
    Optional<City> findById(String id);

    City findByCityName(String cityName);
}
