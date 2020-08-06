package com.mydubbo.dubbo.Service;

import com.mydubbo.dubbo.bean.CityWeather;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface WeatherService {
    String getCityCode(String cityName);
    ArrayList<String> getAllCityCode();
    List<CityWeather> getCityWeather(String cityName);

    /**
     * 持久化指定的城市天气
     * @param cityName
     * @return
     */
    String cacheWeatherInfo(String cityName);
}
