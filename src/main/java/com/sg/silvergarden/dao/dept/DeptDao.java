package com.sg.silvergarden.dao.dept;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class DeptDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate = null;

    public List<Map<String, Object>> deptList(Map<String, Object> pmap) {
        log.info("deptList-dao 호출");
        List<Map<String, Object>> list = sqlSessionTemplate.selectList("deptMapper.deptList", pmap);
        log.info(list);

        return list;
    }

    public List<Map<String, Object>> deptCheck(Map<String, Object> pmap) {
        log.info("deptCheck-dao 호출");
        List<Map<String, Object>> list = sqlSessionTemplate.selectList("deptMapper.deptCheck", pmap);
        log.info(list);

        return list;
    }

    public void deptInsert(Map<String, Object> pmap) {
        log.info("deptinset-dao호출");
        sqlSessionTemplate.insert("deptMapper.deptInsert", pmap);
    }

    public void deptUpdate(Map<String, Object> pmap) {
        log.info("deptUpdate-dao호출");
        sqlSessionTemplate.update("deptMapper.deptUpdate", pmap);
    }

    public void deptDelete(Map<String, Object> pmap) {
        log.info("deptDelete-dao호출");
        sqlSessionTemplate.update("deptMapper.deptDelete", pmap);
    }

    public List<Map<String, Object>> jobList(Map<String, Object> pmap) {
        log.info("jobList-dao 호출");
        List<Map<String, Object>> list = sqlSessionTemplate.selectList("deptMapper.jobList", pmap);
        log.info(list);

        return list;
    }

    public void jobInsert(Map<String, Object> pmap) {
        log.info("jobInsert-dao호출");
        sqlSessionTemplate.insert("deptMapper.jobInsert", pmap);
    }

    public void jobDelete(Map<String, Object> pmap) {
        log.info("jobDelete-dao호출");
        sqlSessionTemplate.update("deptMapper.jobDelete", pmap);
    }

    public List<Map<String, Object>> empList(Map<String, Object> pmap) {
        log.info("empList-dao 호출");
        List<Map<String, Object>> list = sqlSessionTemplate.selectList("deptMapper.deptemplist", pmap);
        log.info(list);

        return list;
    }
}
