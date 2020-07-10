package com.mydubbo.dubbo.Service.dao;

import com.mydubbo.dubbo.bean.CityInfo;
import com.mydubbo.dubbo.bean.reviewopinion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = CityInfo.class, idClass = String.class)
public interface CityInfoRepositry extends JpaRepository<CityInfo,String> {
    @Query("select citycode from CityInfo u where u.cityname=?1")
    String findcityCode(String cityName);

    @Query("select cityname from CityInfo u")
    List<String> findTop100(Pageable pageable);
}
