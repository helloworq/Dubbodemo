package com.mydubbo.dubbo.Service.dao;

import com.mydubbo.dubbo.bean.CityInfo;
import com.mydubbo.dubbo.bean.CityWeather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = CityWeather.class, idClass = String.class)
public interface CityWeatherRepositry extends JpaRepository<CityWeather,String> {
    List<CityWeather> findByCity(String cityName, Pageable pageable);
}
