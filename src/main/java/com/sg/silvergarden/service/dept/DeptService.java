package com.sg.silvergarden.service.dept;

import com.sg.silvergarden.dao.dept.DeptDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class DeptService {

    @Autowired
    DeptDao deptDao = null;

    public List<Map<String, Object>> deptList(Map<String, Object> pmap){
        log.info("deptList-service 호출");
        List<Map<String, Object>> list = new ArrayList<>();
        list = deptDao.deptList(pmap);

        return list;
    }

    public int deptCheck(Map<String, Object> pmap){
        log.info("deptCheck-service 호출");
        List<Map<String, Object>> list = new ArrayList<>();
        list = deptDao.deptCheck(pmap);

        if(list.isEmpty()){
            log.info("중복된 값이 없습니다");
            return 1;
        } else {
            log.info("중복된 값이 있습니다");
            return 0;}
    }

    public void deptInsert(Map<String, Object> pmap){
        log.info("deptInsert-service 호출");
        deptDao.deptInsert(pmap);

    }

    public void deptUpdate(Map<String, Object> pmap){
        log.info(pmap);
        log.info("deptUpdate-service 호출");
        deptDao.deptUpdate(pmap);
    }

    public void deptDelete(Map<String, Object> pmap){
        log.info("deptDelete-service 호출");
        deptDao.deptDelete(pmap);
    }

    public List<Map<String, Object>> jobList(Map<String, Object> pmap) {
        log.info("jobList-service 호출");
        List<Map<String, Object>> list = new ArrayList<>();
        list = deptDao.jobList(pmap);

        return list;

    }

    public void jobInsert(Map<String, Object> pmap) {
        log.info("jobInsert-service 호출");
        deptDao.jobInsert(pmap);
    }

    public void jobDelete(Map<String, Object> pmap) {
        log.info("jobDelete-service 호출");
        deptDao.jobDelete(pmap);
    }

    public List<Map<String, Object>> empList(Map<String, Object> pmap) {
        log.info("empList-service 호출");
        List<Map<String, Object>> list = new ArrayList<>();
        list = deptDao.empList(pmap);

        return list;
    }
}
