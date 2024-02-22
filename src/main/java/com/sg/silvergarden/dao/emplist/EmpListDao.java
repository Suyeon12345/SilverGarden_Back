package com.sg.silvergarden.dao.emplist;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
@Slf4j
public class EmpListDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    public List<Map<String, Object>> allEmpList(Map<String, Object> eMap) {
        List<Map<String, Object>> eList = null;
        eList=sqlSessionTemplate.selectList("allEmpList",eMap);
        return eList;
    }

}
