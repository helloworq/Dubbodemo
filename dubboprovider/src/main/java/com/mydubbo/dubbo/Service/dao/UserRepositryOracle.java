package com.mydubbo.dubbo.Service.dao;

import com.mydubbo.dubbo.bean.UserInfo;
import com.mydubbo.dubbo.bean.reviewopinion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = reviewopinion.class, idClass = String.class)
public interface UserRepositryOracle extends JpaRepository<reviewopinion,String> {
    reviewopinion getById(Long ID);
}
