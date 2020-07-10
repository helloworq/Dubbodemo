package com.mydubbo.dubbo.Service.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mydubbo.dubbo.Service.dao.CityInfoRepositry;
import com.mydubbo.dubbo.Service.dao.CityWeatherRepositry;
import com.mydubbo.dubbo.bean.CityWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheWeatherData {
    @Autowired
    CityInfoRepositry cityInfoRepositry;
    @Autowired
    CityWeatherRepositry cityWeatherRepositry;
    public String cacheData(String cityName){
        //测试通过接口获取数据的情况
        //获取城市代码
        String cityCode=cityInfoRepositry.findcityCode(cityName);
        if (null==cityCode){
            return "城市信息不存在";
        }
        else {
            String msg = GetUrlMessage.getMessage(DownLoad_Configs.weatherAPI + cityCode);
            JSONObject jsonObject = JSONObject.parseObject(msg);
            //第一部分需要解析的数据
            JSONObject jsonObjectPartOne = (JSONObject) jsonObject.get("cityInfo");
            System.out.println(jsonObjectPartOne.get("city"));
            //第二部分需要解析的数据
            JSONObject jsonObjectPartTwo = (JSONObject) jsonObject.get("data");
            //第三部分需要解析的数据
            JSONArray jsonArray = JSONArray.parseArray(jsonObjectPartTwo.get("forecast").toString());
            for (int i = 0; i < jsonArray.size(); i++) {
                //JSONObject jsonObject=(JSONObject) jsonArray.get(i);
                //CityInfo cityInfo=JSONObject.parseObject(jsonObject.toJSONString(),CityInfo.class);
                //测试拼接json数据
                JSONObject jsonObjectRes = new JSONObject();
                //拼接第一部分
                jsonObjectRes.put("city", jsonObjectPartOne.get("city"));
                jsonObjectRes.put("citykey", jsonObjectPartOne.get("citykey"));
                jsonObjectRes.put("updateTime", jsonObjectPartOne.get("updateTime"));
                //拼接第二部分
                jsonObjectRes.put("shidu", jsonObjectPartTwo.get("shidu"));
                jsonObjectRes.put("quality", jsonObjectPartTwo.get("quality"));
                jsonObjectRes.put("wendu", jsonObjectPartTwo.get("wendu"));
                jsonObjectRes.put("ganmao", jsonObjectPartTwo.get("ganmao"));
                //拼接第三部分
                JSONObject jsonObjectArray = JSONObject.parseObject(jsonArray.get(i).toString());
                jsonObjectRes.put("hightemp", jsonObjectArray.get("high"));
                jsonObjectRes.put("lowtemp", jsonObjectArray.get("low"));
                jsonObjectRes.put("ymd", jsonObjectArray.get("ymd"));
                jsonObjectRes.put("fx", jsonObjectArray.get("fx"));
                jsonObjectRes.put("fl", jsonObjectArray.get("fl"));
                jsonObjectRes.put("type", jsonObjectArray.get("type"));
                jsonObjectRes.put("notice", jsonObjectArray.get("notice"));
                //System.out.println("已保存，当前进度---"+i+"---"+cityInfo.getCityname());
                System.out.println("第---" + i + "---组值已成功组装---" + jsonObjectRes.toJSONString());
                System.out.println("将数据转换成对象");
                CityWeather cityWeather = JSONObject.parseObject(jsonObjectRes.toJSONString(), CityWeather.class);
                cityWeatherRepositry.save(cityWeather);
            }
            //System.out.println(cityInfoRepositry.findcityCode("北京"));
            return "缓存完成";
        }
    }
}
