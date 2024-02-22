package com.sg.silvergarden.service.emplist;

import com.sg.silvergarden.dao.emplist.EmpListDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
@Slf4j
public class EmpListService {
    @Autowired
   EmpListDao empListDao=null;
    public List<Map<String, Object>> allEmpList(Map<String, Object> eMap) {
        List<Map<String, Object>> eList = null;
        eList = empListDao.allEmpList(eMap);
        return eList;
    }

}
