package com.mydubbo.dubbo.Controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.mydubbo.dubbo.Service.GetDatabaseMsgService;
import com.mydubbo.dubbo.Service.SayhelloService;
import com.mydubbo.dubbo.bean.UserInfo;
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

    @ResponseBody
    @RequestMapping("/getInfo")
    public String getInfo(){
        return sayhelloService.printMsg("成功了吗？");
    }

    @ResponseBody
    @RequestMapping(value = "/getmsg",method = RequestMethod.POST)
    public List<UserInfo> getmsg(@RequestParam("email")String email){
        //getDatabaseMsgService.generaterData(10);
        System.out.println("请求进入");
        return getDatabaseMsgService.getMsgByEmail(email);
    }

}
