package com.mydubbo.dubbo;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mydubbo.dubbo.Service.dao.CityInfoRepositry;
import com.mydubbo.dubbo.Service.dao.CityWeatherRepositry;
import com.mydubbo.dubbo.Service.util.DownLoad_Configs;
import com.mydubbo.dubbo.Service.util.GetUrlMessage;
import com.mydubbo.dubbo.bean.CityInfo;
import com.mydubbo.dubbo.bean.CityWeather;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class DubboproviderApplicationTests{
    @Resource
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    CityInfoRepositry cityInfoRepositry;
    @Autowired
    CityWeatherRepositry cityWeatherRepositry;
    private final static Logger logger = LoggerFactory.getLogger(DubboproviderApplicationTests.class);

    @Test
    void contextLoads() throws InterruptedException {
        //redisTemplate.opsForValue().set("dasd","dasdsa");
        //System.out.println(redisTemplate.opsForValue().get("dasd"));
        //读取文件获取城市代码的方式太慢了，所以将数据存入数据库里
        /**
         * 获取到的json数据有三个部分，需要分别获取
         */
            /*
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\12733\\Desktop\\dubbo学习记录\\cityweather.json"));
            String str="";
            String prev;
            while ((prev = in.readLine()) != null) {
                str+=prev;
            }
             */
            //测试通过接口获取数据的情况
        System.out.println(cityInfoRepositry.findTop100(PageRequest.of(0,100)).size());
        List<String> list=cityInfoRepositry.findTop100(PageRequest.of(0,100));
        //Array place=JSON.toJSONString(list);
        for (int j = 0; j < list.size(); j++) {
            String cityCode = cityInfoRepositry.findcityCode(list.get(j));
            if (null == cityCode) {
                System.out.println("城市信息不存在");
                continue;
            } else {
                try {
                    String msg = GetUrlMessage.getMessage(DownLoad_Configs.weatherAPI + cityCode);
                    JSONObject jsonObject = JSONObject.parseObject(msg);
                    //第一部分需要解析的数据
                    JSONObject jsonObjectPartOne = (JSONObject) jsonObject.get("cityInfo");
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
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("空指针异常");
                }
            }
            System.out.println("等待1秒开始下一轮数据获取");
            Thread.sleep(1000);
        }
    }
}
