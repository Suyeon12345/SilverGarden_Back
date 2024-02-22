package com.sg.silvergarden.dao.approval;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ApprovalDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    public List<Map<String, Object>> allApprovalList(String e_no) {
        log.info("ApprovalDao: allApprovalList");
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("allApprovalList",e_no);
        return dList;
    }

    public List<Map<String, Object>> getDeptData() {
        log.info("ApprovalDao: getDeptData");
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("getDeptData");
        return dList;
    }
    public Map<String, Object> getApprovalDocCount(String e_no) {
        log.info("ApprovalDao: getApprovalDocCount");
        Map<String, Object> rmap = null;
        rmap = sqlSessionTemplate.selectOne("getApprovalDocCount", e_no);
        return rmap;
    }

    public List<Map<String, Object>> getApprovalDetail(int d_no) {
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("getApprovalDetail",d_no);
        log.info(dList.toString());
        return dList;
    }

    public int approvalInsert(Map<String, Object> pmap) {
        int result = -1;
        result = sqlSessionTemplate.insert("approvalInsert", pmap);
        return result;
    }

    public int fileUpload(List<Map<String, Object>> fileList) {
        int result = -1;
        result = sqlSessionTemplate.insert("approvalFileUpload", fileList);
        return result;
    }

    public int approvalHistoryInsert(List<Map<String, Object>> line) {
        int result = -1;
        result = sqlSessionTemplate.insert("approvalHistoryInsert", line);
        return result;
    }

    public List<Map<String, Object>> approvalWaitList(String e_no) {
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("approvalWaitList",e_no);
        return dList;
    }

    public int passOrDeny(Map<String, Object> pmap) {
        int result = -1;
        result = sqlSessionTemplate.update("passOrDeny",pmap);
        return result;
    }

    public int getFinalApprovalLevel(int d_no){
        int result = -1;
        result = sqlSessionTemplate.selectOne("getFinalApprovalLevel", d_no);
        return result;
    }

    public int statusUpdate(Map<String, Object> pmap){
        int result = -1;
        result =sqlSessionTemplate.update("statusUpdate", pmap);
        return result;
    }

    public int approvalDelete(int d_no){
        int result = -1;
        result =sqlSessionTemplate.delete("approvalDelete", d_no);
        return result;
    }

    public List<Map<String, Object>> approvalCompleteList(String e_no) {
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("approvalCompleteList",e_no);
        return dList;
    }

    public List<Map<String, Object>> approvalDenyList(String e_no) {
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("approvalDenyList",e_no);
        return dList;
    }

    public List<Map<String, Object>> approvalProgressList(String e_no) {
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("approvalProgressList",e_no);
        return dList;
    }

    public List<Map<String, Object>> approvalTempList(String e_no) {
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("approvalTempList",e_no);
        return dList;
    }

}
