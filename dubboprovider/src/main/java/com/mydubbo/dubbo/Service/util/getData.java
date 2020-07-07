package com.mydubbo.dubbo.Service.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;

@Slf4j
@Component
public class getData {
    String getCityCode(String cithName){
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
                if(jsonObject.containsValue(cithName)) {
                    in.close();
                    return jsonObject.get("city_code").toString();
                }
            }
        } catch (IOException e) {
            log.warn("============读取失败================");
        }
        return "目标城市代码不存在";
    }
}
