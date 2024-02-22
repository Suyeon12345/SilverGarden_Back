package com.sg.silvergarden.service.approval;

import com.google.gson.Gson;
import com.sg.silvergarden.dao.approval.ApprovalDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ApprovalService {
    @Autowired
    ApprovalDao approvalDao;
    public List<Map<String, Object>> allApprovalList(String e_no) {
        log.info("approvalService: allApprovalList");
        List<Map<String, Object>> dList = null;
        dList = approvalDao.allApprovalList(e_no);
        log.info(dList.toString());
        return dList;
    }

    public List<Map<String, Object>> getDeptData() {
        log.info("approvalService: getDeptData");
        List<Map<String, Object>> dList = null;
        dList = approvalDao.getDeptData();
        return dList;
    }

    public Map<String, Object> getApprovalDocCount(String e_no) {
        log.info("ApprovalDao: getApprovalDocCount");
        Map<String, Object> rmap = null;
        rmap = approvalDao.getApprovalDocCount(e_no);
        return rmap;
    }

    public List<Map<String, Object>> getApprovalDetail(int d_no) {
        List<Map<String, Object>> dList = null;
        dList = approvalDao.getApprovalDetail(d_no);
        log.info(dList.toString());
        return dList;
    }

    @Transactional
    public int approvalInsert(Map<String, Object> pmap) {//결재문서, 결재이력, 첨부파일을 한번에 생성하는 메서드
        int result = -1;
        Gson g = new Gson();
        List<Map<String, Object>> line = null;
        if(pmap.containsKey("fileList")){
            log.info("파일 있음");
            result = approvalDao.approvalInsert(pmap);
            List<Map<String, Object>> fileList = (List)pmap.get("fileList");
            for(Map<String, Object> map : fileList){//파일리스트에 d_no를 넣어줌
                map.put("d_no", pmap.get("d_no"));
            }
            result = approvalDao.fileUpload(fileList);
            if(pmap.containsKey("line")){//결재라인이 있는 경우에만 결재이력을 생성
                line = g.fromJson(pmap.get("line").toString(), List.class);
                for(Map<String, Object> map : line){//결재라인리스트에 d_no를 넣어줌
                    map.put("d_no", pmap.get("d_no"));
                }
                result = approvalDao.approvalHistoryInsert(line);
            }
        }else{
            log.info("파일 없음");
            result = approvalDao.approvalInsert(pmap);
            if(pmap.containsKey("line")){//결재라인이 있는 경우에만 결재이력을 생성
                line = g.fromJson(pmap.get("line").toString(), List.class);
                log.info(line.toString());
                for(Map<String, Object> map : line){//결재라인리스트에 d_no를 넣어줌
                    map.put("d_no", pmap.get("d_no"));
                }
                result = approvalDao.approvalHistoryInsert(line);
            }
        }
        log.info(String.valueOf(result));
        return result;
    }

    @Transactional
    public int passOrDeny(Map<String, Object> pmap) {
        int result = -1;
        int finalApprovalLev = approvalDao.getFinalApprovalLevel((int)pmap.get("d_no"));//현재 문서의 최종결재단계를 얻어옴

        if("반려".equals(pmap.get("ap_result"))){//결재결과가 반려라면
            approvalDao.passOrDeny(pmap);//결재이력을 반려로 업데이트
            pmap.put("d_status", "반려");
            result = approvalDao.statusUpdate(pmap);//결재문서상태를 반려로 바꿈
        }else{
            if(finalApprovalLev == (int)pmap.get("ap_lev") && "결재".equals(pmap.get("ap_category"))){
                //현재 단계가 최종결재이면서 카테고리가 '걸재'라면
                approvalDao.passOrDeny(pmap);
                pmap.put("d_status", "종결");
                result = approvalDao.statusUpdate(pmap);
            }
            else{//죄종결재자가 아니라면
                approvalDao.passOrDeny(pmap);
                pmap.put("d_status", "진행중");
                result = approvalDao.statusUpdate(pmap);
            }
        }
        return result;
    }

    public List<Map<String, Object>> approvalWaitList(String e_no) {
        log.info("approvalService: approvalWaitList");
        List<Map<String, Object>> dList = null;
        dList = approvalDao.approvalWaitList(e_no);
        return dList;
    }

    public List<Map<String, Object>> approvalCompleteList(String e_no) {
        List<Map<String, Object>> dList = null;
        dList = approvalDao.approvalCompleteList(e_no);
        log.info(dList.toString());
        return dList;
    }

    public List<Map<String, Object>> approvalDenyList(String e_no) {
        List<Map<String, Object>> dList = null;
        dList = approvalDao.approvalDenyList(e_no);
        log.info(dList.toString());
        return dList;
    }

    public List<Map<String, Object>> approvalProgressList(String e_no) {
        List<Map<String, Object>> dList = null;
        dList = approvalDao.approvalProgressList(e_no);
        log.info(dList.toString());
        return dList;
    }

    public List<Map<String, Object>> approvalTempList(String e_no) {
        List<Map<String, Object>> dList = null;
        dList = approvalDao.approvalTempList(e_no);
        log.info(dList.toString());
        return dList;
    }

    public int approvalDelete(int d_no) {
        int result = -1;
        result =approvalDao.approvalDelete(d_no);
        return result;
    }

    public int approvalWithdrawal(int d_no) {
        int result = -1;
        Map<String, Object> map = new HashMap<>();
        map.put("d_no", d_no);
        map.put("d_status", "임시저장");
        result =approvalDao.statusUpdate(map);
        return result;
    }
}
