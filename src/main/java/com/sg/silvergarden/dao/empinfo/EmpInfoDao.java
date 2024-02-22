package com.sg.silvergarden.dao.empinfo;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmpInfoDao {
    Logger logger = LoggerFactory.getLogger(EmpInfoDao.class);
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<Map<String, Object>> empList(Map<String, Object> eMap) {
        logger.info("empList");
        List<Map<String, Object>> eList = sqlSessionTemplate.selectList("empList", eMap);
        logger.info(eList.toString());
        return eList;
    }

    public List<Map<String, Object>> experienceList(Map<String, Object> eMap) {
        logger.info("experienceList");
        List<Map<String, Object>> eList = sqlSessionTemplate.selectList("experienceList", eMap);
        logger.info(eList.toString());
        return eList;
    }

    public int empUpdate(Map<String, Object> eMap) {
        logger.info("empUpdate");
        int result = 0;
        try {
            result = sqlSessionTemplate.update("empInfoUpdate", eMap);
        } catch (Exception e) {
            logger.info(e.toString());
        }
        return result;
    }

    public int empDelete(int e_no) {
        logger.info("empDelete");
        int result = 0;
        try {
            result = sqlSessionTemplate.delete("empDelete", e_no);
        } catch (Exception e) {
            logger.info(e.toString());
        }
        return result;
    }

/*    public int empEduUpdate(Map<String, Object> eMap) {
        logger.info("empEduUpdate");
        logger.info(eMap.toString());
        int result = 0;
        try {
            result = sqlSessionTemplate.update("empEduUpdate", eMap);
        } catch (Exception e) {
            *//*logger.info(e.toString());*//*
            e.printStackTrace();
        }
        return result;
    }*/
/*    public int empEduUpdate(Map<String, Object> eMap) {
        logger.info("empEduUpdate");
        logger.info(eMap.toString());
        int result = 0;
        // 모든 키에 대해 반복하면서 "HIGH_SCHOOL"로 시작하는 키가 있는지 확인
        for (String key : eMap.keySet()) {
            if (key.startsWith("HIGH_SCHOOL")) {
                try {
                    // 업데이트를 위한 Map 생성
                    Map<String, Object> eduMap = new HashMap<>();
                    eduMap.put("MAJOR", eMap.get(key)); // 해당 키의 값을 MAJOR로 설정
                    eduMap.put("ORDER", 1); // EDU_ORDER가 1인 곳에 대해서 업데이트

                    // EDU_ORDER가 1인 곳의 EDU_MAJOR 업데이트
                    result = sqlSessionTemplate.update("empEduUpdate", eduMap);
                } catch (Exception e) {
                    *//*logger.info(e.toString());*//*
                    e.printStackTrace();
                }
            }
        }

        return result;
    }*/

    public int empEduUpdate(Map<String, Object> eMap) {
        logger.info("empEduUpdate");
        logger.info(eMap.toString());

        int result = 0;

        // 프론트에서 받아온 E_NO 값을 가져옴
        Object eNo = eMap.get("E_NO");

        // 모든 키에 대해 반복하면서 조건에 따라 업데이트 쿼리 수행
        for (String key : eMap.keySet()) {
            try {
                // 해당 키의 값을 가져옴
                Object value = eMap.get(key);

                // 해당 키의 EDU_ORDER 값 설정
                int eduOrder;
                if (key.startsWith("HIGH_SCHOOL")) {
                    eduOrder = 1;
                } else if (key.startsWith("UNIVERSITY")) {
                    eduOrder = 2;
                } else if (key.startsWith("GRADUATE_SCHOOL")) {
                    eduOrder = 3;
                } else {
                    continue; // 다른 키는 무시
                }

                // 업데이트를 위한 맵 생성
                Map<String, Object> eduMap = new HashMap<>();
                eduMap.put("E_NO", eNo); // E_NO 추가
                eduMap.put("VALUE", value); // 해당 키의 값을 VALUE로 설정
                eduMap.put("EDU_ORDER", eduOrder); // 해당 키의 EDU_ORDER 값 설정
                eduMap.put("key", key);
                logger.info(eduMap.toString());

                // 업데이트 쿼리 실행
                result += sqlSessionTemplate.update("empEduUpdate", eduMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public int experienceInsert(Map<String, Object> eMap) {
        logger.info("experienceInsert");
        logger.info(eMap.toString());
        int result = 0;
        result = sqlSessionTemplate.insert("experienceInsert", eMap);
        return result;
    }

    public int experienceDelete(int exp_no) {
        logger.info("experienceDelete");
        int result = 0;
        try {
            result = sqlSessionTemplate.delete("experienceDelete", exp_no);
        } catch (Exception e) {
            logger.info(e.toString());
        }
        return result;
    }

    public int certiInsert(Map<String, Object> eMap) {
        logger.info("certiInsert");
        logger.info(eMap.toString());
        int result = 0;
        result = sqlSessionTemplate.insert("certiInsert", eMap);
        return result;
    }
}
