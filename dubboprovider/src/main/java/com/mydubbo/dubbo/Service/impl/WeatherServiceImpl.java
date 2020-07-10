package com.mydubbo.dubbo.Service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mydubbo.dubbo.Service.WeatherService;
import com.mydubbo.dubbo.Service.dao.CityInfoRepositry;
import com.mydubbo.dubbo.Service.dao.CityWeatherRepositry;
import com.mydubbo.dubbo.Service.util.CacheWeatherData;
import com.mydubbo.dubbo.Service.util.GetWeatherData;
import com.mydubbo.dubbo.bean.CityWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    GetWeatherData getWeatherData;
    @Autowired
    CacheWeatherData cacheWeatherData;
    @Autowired
    CityInfoRepositry cityInfoRepositry;
    @Autowired
    CityWeatherRepositry cityWeatherRepositry;
    @Override
    public String getCityCode(String cityName) {
        return cityInfoRepositry.findcityCode(cityName);
    }

    @Override
    public List<CityWeather> getCityWeather(String cityName) {
        Pageable pageable= PageRequest.of(0,1);
        return cityWeatherRepositry.findByCity(cityName,pageable);
    }

    @Override
    public String cacheWeatherInfo(String cityName) {
        return cacheWeatherData.cacheData(cityName);
    }
}
