package com.sg.silvergarden.dao.attendance;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AttendanceDao {
    Logger logger = LoggerFactory.getLogger(AttendanceDao.class);
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<Map<String, Object>> atList(Map<String, Object> atMap) {
        logger.info("atList");
        return sqlSessionTemplate.selectList("atList", atMap);
    }

    public int atInsert(Map<String, Object> atMap) {
        logger.info("atInsert");
        return sqlSessionTemplate.insert("atInsert", atMap);
    }

    public int atUpdate(Map<String, Object> atMap) {
        logger.info("atUpdate");
        return sqlSessionTemplate.update("atUpdate", atMap);
    }

    public int adminAtUpdate(Map<String, Object> atMap) {
        logger.info("adminAtUpdate");
        logger.info(atMap.toString());
        int result = 0;
        try {
            result = sqlSessionTemplate.update("adminAtUpdate", atMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void noneAtInsert() {
        String sql = "INSERT INTO ATTENDANCE (AT_DATE, E_NO, AT_START, AT_STATUS, REG_DATE, REG_ID)" +
                "SELECT E_E.NO, TRUNC(SYSDATE), SYSDATE, '결근', SYSDATE, '일괄처리'" +
                "FROM EMPLOYEE E" +
                "WHERE NOT EXISTS (SELECT 1 FROM ATTENDANCE WHERE AT_DATE = TRUNC(SYSDATE) AND E_NO = E.E_NO)";
        sqlSessionTemplate.insert("noneAtInsert", sql);
    }
}
