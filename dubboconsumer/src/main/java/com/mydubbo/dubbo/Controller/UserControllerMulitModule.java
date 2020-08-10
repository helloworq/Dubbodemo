package com.mydubbo.dubbo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.mydubbo.dubbo.prev.User;
import com.mydubbo.dubbo.prev.UserContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

/**
 * 控制器有两个接口，第一个接口返回所有的yml配置文件名以及配置内容
 *                 第二个接口则是根据传输进来的yml配置信息以及配置名重新生成yml文件
 */

/**
 * 多模块读取
 */
@RestController
public class UserControllerMulitModule {
    @Autowired
    BizConfig bizConfig;
    @Autowired
    UserContainer userContainer;
    @Autowired
    ContextRefresher contextRefresher;

    public String getPathPrefix() {
        return pathPrefix;
    }

    public void setPathPrefix(String pathPrefix) {
        this.pathPrefix = pathPrefix;
    }

    public String pathPrefix;

    @GetMapping("getYmlInfo")
    public HashMap<String,Object> getYmlInfo(
            @RequestParam(value = "modulename") String modulename,
            @RequestParam(value = "ymlFilename") String ymlFilename
    ) throws IOException {
        System.out.println(modulename+"=="+ymlFilename);
        HashMap<String,Object> hashMap=new HashMap<>();
        //获取完整路径，供参数拼接,originpath典型样例:
        //file:/C:/Users/12733/Desktop/dubbo学习记录/dubbotest/dubboconsumer/target/classes/application-dev.yml
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:*.yml");
        String originPath=Arrays.stream(resources).findFirst().get().getURL().toString();
        //根据参数拼接目标路径
        String filePath=pathPrefix+modulename+"/target/classes/"+ymlFilename;
        File file=new File(filePath);
        //开始获取yml文件的内容
        Yaml yaml = new Yaml();
        //非空判断
        if (null!=yaml.load(new FileInputStream(file))) {
            hashMap.put(ymlFilename.split("\\.")[0], yaml.load(new FileInputStream(file)));
        }
        return hashMap;
    }

    /**
     * 此方法用于在页面加载完成的时候获取模块名以及文件名数据
     * //@param ymlModulename
     * //@param ymlFilename
     * @return
     * @throws IOException
     */
    @GetMapping("getYmlInfoBase")
    public HashMap<String,Object> getYmlInfoBase() throws IOException {
        //多模块获取yml文件，传入的参数应该有模块名和文件名
        HashMap<String,Object> hashMap=new HashMap<>();
        //统一使用Arrays.stream(resources).findFirst().get().getURL()这个方法获取数据
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:*.yml");
        String originPath=Arrays.stream(resources).findFirst().get().getURL().toString();
        //originPath的典型返回结果
        //file:/C:/Users/12733/Desktop/dubbo学习记录/dubbotest/dubboconsumer/target/classes/application-dev.yml
        //获取到上述路径并没有依赖其他可能改变的方法，故只需要替换模块名dubboconsumer和文件名application-dev.yml
        //模块名的位置的确定方法为:将完整路径以/切分，再查找如果有两个部分是target和classes并且是连续的就获取target前面
        //的那个部分,这样最大限度确保获取到的是模块名
        //先获取一个模块名
        String[] prevModulenameArr=originPath.split("/");
        String prevModelueName="";
        for (int i = 0; i < prevModulenameArr.length; i++) {
            if (prevModulenameArr[i].equals("target")&&prevModulenameArr[i+1].equals("classes"))
                prevModelueName=prevModulenameArr[i-1];
        }
        //=======================测试输出全部模块名=================================
        String path=Arrays.stream(resources).findFirst().get().getURL().toString().substring(6);
        String filepath=path.substring(0,path.indexOf(prevModelueName));
        ArrayList<String> moduleName=new ArrayList<>();
        try {
            File file = new File(filepath);
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                if (filelist[i].indexOf("dubbo")!=-1)
                    moduleName.add(filelist[i]);
            }
            hashMap.put("moduleName",moduleName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //=======================测试输出全部模块名=================================
        //=======================测试输出全部yml文件名==============================
        //开始根据之前获取到的模块名拼接文件路径
        //file:/C:/Users/12733/Desktop/dubbo学习记录/dubbotest/dubboconsumer/target/classes/application-dev.yml

        pathPrefix=originPath.substring(6,originPath.indexOf(moduleName.get(0)));
        try {
            for (String s:moduleName) {
                ArrayList<String> ymlName=new ArrayList<>();
                String filepathYml=pathPrefix+s+"/target/classes";
                File file = new File(filepathYml);
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    if (filelist[i].indexOf("application")!=-1)
                        ymlName.add(filelist[i]);
                }
                hashMap.put(s,ymlName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //=======================测试输出全部yml文件名==============================
        return hashMap;
    }


    /**
     * 修改信息有两种情况，第一种是修改classes目录下的文件这种情况不会
     * @param ymlInfoAfterChangingContent   前端传入修改后的json数据
     * @param ymlFilename                   前端传入需修改的文件名(需要带.yml后缀)
     * @return
     * @throws IOException
     */
    @PostMapping("setYmlInfo1")
    public HashMap<String,String> setYmlInfo(
            @RequestParam(value = "ymlInfoAfterChangingContent") String ymlInfoAfterChangingContent,//使用@Requestbody接收json数据
            @RequestParam(value = "ymlFilename") String ymlFilename) throws IOException {
        System.out.println("开始处理");
        HashMap<String,String> hashMap=new HashMap<>();
        //获取resources目录下所有后缀为yml的文件名,例如(application.yml)
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:*.yml");
        String filePathOrigin=String.valueOf(Arrays.stream(resources).findFirst().get().getURL());
        Integer startPosition=filePathOrigin.indexOf("/");
        Integer endPosition=filePathOrigin.indexOf("application");
        //System.out.println(filePathOrigin.substring(startPosition+1,endPosition)+ymlFilename);
        System.out.println(filePathOrigin.substring(startPosition+1,endPosition));
        File file=new File(filePathOrigin.substring(startPosition+1,endPosition)+ymlFilename.substring(ymlFilename.indexOf("application")));
        FileWriter fileWriter = new FileWriter(file);
        //将传入的数据以json格式写进yml
        fileWriter.write(ymlInfoAfterChangingContent.trim());
        fileWriter.flush();
        fileWriter.close();
        hashMap.put("msg","success");
        //修改完成之后刷新容器
        refresh();
        refresh();
        return hashMap;
    }
    /**
     * 显示信息
     * @return
     */
    @GetMapping(path = "/show1")
    public String show() {
        JSONObject res = new JSONObject();
        res.put("biz", JSONObject.toJSONString(bizConfig));
        return res.toJSONString();
    }

    /**
     * 刷新配置
     * @return
     */
    @PostMapping(path = "/refresh1")
    public String refresh() {
        new Thread(() -> contextRefresher.refresh()).start();
        return show();
    }
}
