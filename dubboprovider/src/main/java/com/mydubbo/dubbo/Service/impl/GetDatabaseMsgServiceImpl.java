package com.mydubbo.dubbo.Service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mydubbo.dubbo.Service.GetDatabaseMsgService;
import com.mydubbo.dubbo.Service.dao.UserRepositry;
import com.mydubbo.dubbo.bean.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class GetDatabaseMsgServiceImpl implements GetDatabaseMsgService {
    String nameString="赵钱孙李周吴郑王冯陈褚卫";
    Integer nameLength=4;
    @Autowired
    UserRepositry userRepositry;
    @Override
    public List<UserInfo> getMsgByEmail(String Email) {
        System.out.println("进入。。。");
        return userRepositry.findByEmail(Email);
    }

    @Override
    public void generaterData(Integer Datarows) {
        for (int i = 0; i < Datarows; i++) {
            String username="";
            String password="";
            for (int j = 0; j < 4; j++) {
                char usernameCharacter= nameString.toCharArray()[(int)(Math.random()*(nameString.length()))];
                username+=usernameCharacter;
            }
            UserInfo userInfo=new UserInfo();
            userInfo.setUsername(username);
            for (int j = 0; j < 4; j++) {
                char usernameCharacter= nameString.toCharArray()[(int)(Math.random()*(nameString.length()))];
                password+=usernameCharacter;
            }
            userInfo.setPassword(password);
            userInfo.setEmail("123@qq.com");
            this.userRepositry.save(userInfo);
        }
    }
}
