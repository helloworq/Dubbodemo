package com.mydubbo.dubbo.bean;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "tb_cityweather")
@Getter
@Setter
public class CityWeather implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    //第一部分
    @Column(name = "city", length = 64)
    private String city;

    @Column(name = "citycode", length = 64)
    private String citykey;

    @Column(name = "updateTime", length = 64)
    private String updateTime;
    //第二部分
    @Column(name = "shidu", length = 64)
    private String shidu;

    @Column(name = "quality", length = 64)
    private String quality;

    @Column(name = "wendu", length = 64)
    private String wendu;

    @Column(name = "ganmao", length = 64)
    private String ganmao;
    //第三部分
    @Column(name = "hightemp", length = 64)
    private String hightemp;

    @Column(name = "lowtemp", length = 64)
    private String lowtemp;

    @Column(name = "ymd", length = 64)
    private Date ymd;

    @Column(name = "fx", length = 64)
    private String fx;

    @Column(name = "fl", length = 64)
    private String fl;

    @Column(name = "type", length = 64)
    private String type;

    @Column(name = "notice", length = 64)
    private String notice;
}
