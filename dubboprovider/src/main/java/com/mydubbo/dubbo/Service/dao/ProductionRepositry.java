package com.mydubbo.dubbo.Service.dao;

import com.mydubbo.dubbo.bean.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Production.class, idClass = String.class)
public interface ProductionRepositry extends JpaRepository<Production,String> {
    //@Query("select u from Production u where u.productname")
    List<Production> findAllByProductname(String productname);
}