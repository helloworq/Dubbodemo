package com.mydubbo.dubbo.Service.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;

@Slf4j
@Component
public class GetWeatherData {
    /**
     * Date:2020-07-07
     * Author:zhoul
     * operation为则返回城市代码，为1返回城市序号
     * @param cithName
     * @param operation
     * @return
     */
    public String getCityCodeOrOrder(String cithName,Integer operation){
        /**
         * 查文件太慢了，换成查数据库
         */
        return null;
        /*
        try {
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\12733\\Desktop\\dubbo学习记录\\citycode-2019-08-23.json"));
            String str="";
            String prev;
            while ((prev = in.readLine()) != null) {
                str+=prev;
            }
            JSONArray jsonArray=JSONArray.parseArray(str);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject=(JSONObject) jsonArray.get(i);
                if(jsonObject.containsValue(cithName)&&operation==0) {
                    in.close();
                    return jsonObject.get("city_code").toString();
                }
                else if(jsonObject.containsValue(cithName)&&operation==1){
                    in.close();
                    return String.valueOf(Integer.valueOf(jsonObject.get("id").toString())+1);
                }
            }
        } catch (IOException e) {
            log.warn("============读取失败================");
        }
        return "目标城市代码不存在";
         */
    }


}
