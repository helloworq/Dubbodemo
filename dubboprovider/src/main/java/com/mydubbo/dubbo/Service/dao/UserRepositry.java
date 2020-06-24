package com.mydubbo.dubbo.Service.dao;

import com.mydubbo.dubbo.bean.UserInfo;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;


import java.util.List;

@RepositoryDefinition(domainClass = UserInfo.class, idClass = String.class)
public interface UserRepositry extends JpaRepository<UserInfo,String> {

    List<UserInfo> findByUsername(String username);

    List<UserInfo> findByEmail(String email);

    List<UserInfo> findByEmail(String email, Pageable pageable);
}
