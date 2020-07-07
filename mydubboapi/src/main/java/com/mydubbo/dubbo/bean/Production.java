package com.mydubbo.dubbo.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 测试多表查询
 */
@Entity
@Table(name = "tb_production")
@Getter
@Setter
public class Production implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "productname", unique = true, nullable = false, length = 64)
    private String productname;

    @Column(name = "productprize", nullable = false, length = 64)
    private String productprize;

    @Column(name = "producttime", length = 64)
    private String producttime;

}
