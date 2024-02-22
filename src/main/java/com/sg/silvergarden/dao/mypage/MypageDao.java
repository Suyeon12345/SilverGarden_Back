package com.sg.silvergarden.dao.mypage;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
@Slf4j
public class MypageDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    public List<Map<String, Object>> callMypage(Map<String, Object> mMap) {
        List<Map<String, Object>> mlist = null;
        mlist = sqlSessionTemplate.selectList("callMypage",mMap);
        return mlist;

    }
}
