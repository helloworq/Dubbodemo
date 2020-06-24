package com.mydubbo.dubbo.Service;

import com.mydubbo.dubbo.bean.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 尝试一下dubbo+springdatajpa获取信息
 * 步骤一导入依赖：
         * <dependency>
         *     <groupId>org.springframework.boot</groupId>
         *     <artifactId>spring-boot-starter-data-jpa</artifactId>
         * </dependency>
         * <dependency>
         *     <groupId>mysql</groupId>
         *     <artifactId>mysql-connector-java</artifactId>
         *     <scope>runtime</scope>
         * </dependency>
         * <dependency>
         *     <groupId>org.projectlombok</groupId>
         *     <artifactId>lombok</artifactId>
         *     <optional>true</optional>
         * </dependency>
         * <dependency>
         *     <groupId>org.springframework.boot</groupId>
         *     <artifactId>spring-boot-devtools</artifactId>
         *     <optional>true</optional>
         * </dependency>
 * 步骤二：导入springdatajpa的配置信息：
         * server:
         *   port: 8080
         *   servlet:
         *     context-path: /
         * spring:
         *   datasource:
         *     url: jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false
         *     username: root
         *     password: mysql123
         *   jpa:
         *     database: MySQL
         *     database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
         *     show-sql: true
         *     hibernate:
         *       ddl-auto: update
         *
         * ddl-auto:
         * create：每次运行程序时，都会重新创建表，故而数据会丢失
         * create-drop：每次运行程序时会先创建表结构，然后待程序结束时清空表
         * upadte：每次运行程序，没有表时会创建表，如果对象发生改变会更新表结构，原有数据不会清空，只会更新（推荐使用）
         * validate：运行程序会校验数据与数据库的字段类型是否相同，字段不同会报错
         * none: 禁用DDL处理
 * 步骤三：创建实体表（推荐将表放置到entiy包中）
 * package com.example.springbootjpa.entity;
 *
 * import lombok.Data;
 * import org.hibernate.annotations.GenericGenerator;
 *
 * import javax.persistence.*;
 *
 * @Entity
 * @Table(name = "tb_user")
 * @Data
 * public class User {
 *     @Id
 *     @GenericGenerator(name = "idGenerator", strategy = "uuid")
 *     @GeneratedValue(generator = "idGenerator")
 *     private String id;
 *
 *     @Column(name = "username", unique = true, nullable = false, length = 64)
 *     private String username;
 *
 *     @Column(name = "password", nullable = false, length = 64)
 *     private String password;
 *
 *     @Column(name = "email", length = 64)
 *     private String email;
 * }
 * 主键采用UUID策略
 * @GenericGenerator是Hibernate提供的主键生成策略注解，注意下面的@GeneratedValue（JPA注解）使用generator = "idGenerator"引用了上面的name = "idGenerator"主键生成策略
 *
 * 一般简单的Demo示例中只会使用@GeneratedValue(strategy = GenerationType.IDENTITY)这种主键自增的策略，而实际数据库中表字段主键类型很少是int型的
 *
 * JPA自带的几种主键生成策略
 *
 * TABLE： 使用一个特定的数据库表格来保存主键
 * SEQUENCE： 根据底层数据库的序列来生成主键，条件是数据库支持序列。这个值要与generator一起使用，generator 指定生成主键使用的生成器（可能是orcale中自己编写的序列）
 * IDENTITY： 主键由数据库自动生成（主要是支持自动增长的数据库，如mysql）
 * AUTO： 主键由程序控制，也是GenerationType的默认值
 *
 *
 */
public interface GetDatabaseMsgService {
    List<UserInfo> getMsgByEmail(String Email);

    void generaterData(Integer Datarows);

    List<UserInfo> getPageableMsgByEmail(String Email, int currentPage);
}
