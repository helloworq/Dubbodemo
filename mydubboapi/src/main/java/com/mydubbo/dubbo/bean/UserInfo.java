package com.mydubbo.dubbo.bean;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.cache.annotation.CacheEvict;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_userinfo")
@Data
public class UserInfo implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "username", unique = true, nullable = false, length = 64)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "email", length = 64)
    private String email;

}
