package com.mydubbo.dubbo.bean;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "tb_cityinfo")
@Getter
@Setter
public class CityInfo implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "pid", unique = true, length = 64)
    private Integer username;

    @Column(name = "citycode", length = 64)
    private String citycode;

    @Column(name = "cityname", length = 64)
    private String cityname;

    @Column(name = "postcode", length = 64)
    private String postcode;

    @Column(name = "areacode", length = 64)
    private String areacode;

    @Column(name = "ctime", length = 64)
    private Date ctime;
}
