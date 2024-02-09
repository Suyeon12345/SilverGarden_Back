package com.sg.silvergarden.dao.program;

import com.sg.silvergarden.service.program.ProgramService;
import com.sg.silvergarden.vo.program.ProgramVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProgramDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    Logger logger = LoggerFactory.getLogger(ProgramService.class);
    public List<Map<String, Object>> pgList(Map<String, Object> pmap) {
        logger.info("AngelDao-pgList");
        List<Map<String, Object>> pgList = null;
        pgList = sqlSessionTemplate.selectList("pgList", pmap);
        return pgList;
    }

    public int pgInsert(Map<String, Object> pmap) {
        logger.info("AngelDao-pgInsert");
        int result = 0;
        result = sqlSessionTemplate.insert("pgInsert",pmap);
        return result;
    }

    public int pgDelete(int pg_No) {
        logger.info("AngelDao-pgDelete");
        int result = 0;
        result = sqlSessionTemplate.insert("pgDelete",pg_No);
        return result;
    }

    public List<ProgramVO> scheduleList() {
        logger.info("AngelDao-scheduleList");
        List<ProgramVO> calList = null;
        calList = sqlSessionTemplate.selectList("scheduleList");
        return calList;
    }

    public int pgUpdate(Map<String, Object> pmap) {
        logger.info("AngelDao-pgUpdate");
        int result = sqlSessionTemplate.update("pgUpdate", pmap);
        return result;
    }

}
