package com.sg.silvergarden.dao.programcal;
import com.sg.silvergarden.vo.programcal.ProgramSchedule;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProgramScheduleDao {

    Logger logger = LoggerFactory.getLogger(ProgramScheduleDao.class);

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public String test() {
        logger.info("ProgramScheduleDao - test");
        return null;
    }

    public List<Map<String, Object>> getProgramSchedule(Map<String, Object> pmap) {
        logger.info("ProgramScheduleDao - getProgramSchedule");
        return sqlSessionTemplate.selectList("getProgramSchedule", pmap);
    }

    public int addProgramSchedule(Map<String, Object> pamp) {
        logger.info("ProgramScheduleDao - addProgramSchedule");
        return sqlSessionTemplate.insert("addProgramSchedule", pamp);
    }

    public int deleteProgramSchedule(Map<String, Object> psNo2) {
        logger.info("ProgramScheduleDao - deleteProgramSchedule");
        return sqlSessionTemplate.delete("deleteProgramSchedule", psNo2);
    }

    public List<ProgramSchedule> scheduleList() {
        logger.info("ProgramScheduleDao - scheduleList");
        return sqlSessionTemplate.selectList("scheduleList");
    }

    public int updateProgramSchedule(Map<String, Object> pmap) {
        logger.info("ProgramScheduleDao - updateProgramSchedule");
        return sqlSessionTemplate.update("updateProgramSchedule", pmap);
    }
}