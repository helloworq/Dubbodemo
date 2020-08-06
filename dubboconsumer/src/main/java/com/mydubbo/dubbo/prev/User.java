package com.mydubbo.dubbo.prev;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class User {
    /*public ArrayList<HashMap<String,User>> arrHashUser;
    public ArrayList<HashMap<String,String>> arrHash;
    public ArrayList<String> arrlist;
    public HashMap<String,String> haMap;
    public int[] arr;
    public List<User> list;*/
    public int id;
    public String age;
}
