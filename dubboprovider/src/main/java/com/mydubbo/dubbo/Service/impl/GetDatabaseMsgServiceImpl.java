package com.mydubbo.dubbo.Service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mydubbo.dubbo.Service.GetDatabaseMsgService;
import com.mydubbo.dubbo.Service.dao.ProductionRepositry;
import com.mydubbo.dubbo.Service.dao.UserRepositry;
import com.mydubbo.dubbo.Service.dao.UserRepositryOracle;
import com.mydubbo.dubbo.bean.Production;
import com.mydubbo.dubbo.bean.UserInfo;
import com.mydubbo.dubbo.bean.reviewopinion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Service
@Component
public class GetDatabaseMsgServiceImpl implements GetDatabaseMsgService {
    String nameString="赵钱孙李周吴郑王冯陈褚卫";
    String nameStringPro="SoMnIhIiuyvYTFtyFTfRDr6fGYNjo";
    Integer nameLength=4;
    int pageOffset=5;//定义分页偏移量
    @Autowired
    UserRepositry userRepositry;
    @Autowired
    ProductionRepositry productionRepositry;
    @Autowired
    UserRepositryOracle userRepositryOracle;
    @Override
    public List<UserInfo> getMsgByEmail(String Email) {
        System.out.println("进入请求1");
        return userRepositry.findByEmail(Email);
    }

    /**
     * 分页查询数据，PageRequest.of传入的第一个参数就可以完成分页功能，
     * 不需要乘pageOffset
     * @param Email
     * @param currentPage
     * @return
     */
    @Override
    public List<UserInfo> getPageableMsgByEmail(String Email, int currentPage) {
        System.out.println("进入请求2");
        PageRequest pageable=PageRequest.of(currentPage,pageOffset);
        return userRepositry.findByEmail(Email,pageable);
    }

    @Override
    public void generaterDataProduction(Integer Datarows) {
        for (int i = 0; i < Datarows; i++) {
            String productname="";
            Date producttime;
            double productprize=0;
            for (int j = 0; j < 4; j++) {
                char usernameCharacter= nameStringPro.toCharArray()[(int)(Math.random()*(nameStringPro.length()))];
                productname+=usernameCharacter;
            }
            Production production=new Production();
            production.setProductname(productname);
            production.setProductprize(String.valueOf((float) Math.random()*(100)));
            production.setProducttime(new Date().toString());
            this.productionRepositry.save(production);
        }
    }

    @Override
    public reviewopinion getmsgbyid(Long id) {
        return userRepositryOracle.getById(id);
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
