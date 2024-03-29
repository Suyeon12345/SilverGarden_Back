package com.sg.silvergarden.dao.notice;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class NoticeDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public List<Map<String, Object>> noticeList(Map<String, Object> rmap) throws Exception{
        List<Map<String, Object>> nlist = null;
        nlist = sqlSessionTemplate.selectList("noticeList",rmap);
        return nlist;
    }

    public Map<String, Object> noticeTotalCount(Map<String, Object> rmap) throws Exception{
        Map<String, Object> count = null;
        count = sqlSessionTemplate.selectOne("noticeTotalCount",rmap);
        return count;
    }
    public List<Map<String, Object>> noticeDetail(int n_no) throws Exception{
        List<Map<String, Object>> nlist = null;
        nlist = sqlSessionTemplate.selectList("noticeDetail",n_no);
        return nlist;
    }
    public int noticeDelete(Map<String, Object>pmap) throws Exception{
        int result = -1;
        result = sqlSessionTemplate.delete("noticeDelete", pmap);
        return result;
    }
    public int noticeUpdate(Map<String, Object> pmap) throws Exception{
        int result = -1;
        result = sqlSessionTemplate.update("noticeUpdate", pmap);
        return result;
    }
    public int noticeHitCount(int n_no) throws Exception{
        int result = -1;
        result = sqlSessionTemplate.update("noticeHitCount", n_no);
        return result;
    }

    public int fileUpload(List<Map<String, Object>> list) throws Exception{
        int result = -1;
        log.info("fileUpload"+list.toString());
        result = sqlSessionTemplate.insert("fileUpload2", list);
        return result;
    }
    public int noticeInsert(Map<String, Object> pmap) throws Exception{
        int result = -1;
        log.info("noticeInsert");
        result = sqlSessionTemplate.insert("noticeInsert", pmap);
        log.info("시퀀스 값"+pmap.get("n_no").toString());
        return result;
    }

    public int fileDelete(String filename) throws Exception{
        int result = -1;
        result = sqlSessionTemplate.delete("deleteFile", filename);
        return result;
    }
}