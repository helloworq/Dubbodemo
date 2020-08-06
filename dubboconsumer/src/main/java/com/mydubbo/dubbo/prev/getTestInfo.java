package com.mydubbo.dubbo.prev;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mydubbo.dubbo.Service.GetDatabaseMsgService;
import com.mydubbo.dubbo.Service.SayhelloService;
import com.mydubbo.dubbo.Service.WeatherService;
import com.mydubbo.dubbo.bean.CityWeather;
import com.mydubbo.dubbo.bean.UserInfo;
import com.mydubbo.dubbo.bean.reviewopinion;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class getTestInfo {
    @Reference
    SayhelloService sayhelloService;
    @Reference
    GetDatabaseMsgService getDatabaseMsgService;
    @Reference
    WeatherService weatherService;

    @ResponseBody
    @RequestMapping("/getInfo")
    public String getInfo(){
        return sayhelloService.printMsg("成功了吗？");
    }

    @Cacheable("info")
    @ResponseBody
    @RequestMapping("/getCacheInfo")
    public String getCacheInfo(){
        System.out.println("进入方法体");
        return "这是缓存信息";
    }

    @ResponseBody
    @RequestMapping(value = "/getPageableInfo",method = RequestMethod.POST)
    public List<UserInfo> getPageableInfo(
            @RequestParam("email")String email,
            @RequestParam("currentPage")Integer currentPage){
        //getDatabaseMsgService.generaterData(10);
        System.out.println("请求进入2");
        return getDatabaseMsgService.getPageableMsgByEmail(email,currentPage);
    }


    @ResponseBody
    @RequestMapping(value = "/getmsg",method = RequestMethod.POST)
    public List<UserInfo> getmsg(@RequestParam("email")String email){
        //getDatabaseMsgService.generaterData(10);
        System.out.println("请求进入3");
        return getDatabaseMsgService.getMsgByEmail(email);
    }

    @Cacheable("info1")
    @ResponseBody
    @RequestMapping(value = "/getmsgoracle",method = RequestMethod.GET)
    public reviewopinion getmsgoracle(@RequestParam("id")Long id){
        //getDatabaseMsgService.generaterData(10);
        System.out.println("请求进入3");
        return getDatabaseMsgService.getmsgbyid(id);
    }

    @Cacheable("info2")
    @ResponseBody
    @RequestMapping(value = "/getcitycode",method = RequestMethod.GET)
    public String getcitycode(@RequestParam("cityname")String cityname){
        //getDatabaseMsgService.generaterData(10);
        System.out.println("请求进入3");
        return weatherService.getCityCode(cityname);
    }

    @Cacheable("info3")
    @ResponseBody
    @RequestMapping(value = "/getcityweather",method = RequestMethod.GET)
    public List<CityWeather> getcityweather(@RequestParam("cityname")String cityname){
        //getDatabaseMsgService.generaterData(10);
        System.out.println("请求进入3");
        return weatherService.getCityWeather(cityname);
    }

    @Cacheable("info6")
    @ResponseBody
    @RequestMapping(value = "/getcityallcode",method = RequestMethod.GET)
    public List<String> getcityallcode(){
        //getDatabaseMsgService.generaterData(10);
        System.out.println("请求进入6");
        return weatherService.getAllCityCode();
    }

    @ResponseBody
    @RequestMapping(value = "/cachecityweather",method = RequestMethod.GET)
    public String cachecityweather(@RequestParam("cityname")String cityname){
        //getDatabaseMsgService.generaterData(10);
        System.out.println("请求进入3");
        return weatherService.cacheWeatherInfo(cityname);
    }


}
