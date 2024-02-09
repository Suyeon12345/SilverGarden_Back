package com.sg.silvergarden.service.notice;

import com.sg.silvergarden.dao.notice.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class NoticeService {

    @Autowired
    NoticeDao noticeDao;
    public List<Map<String, Object>> noticeList(Map<String, Object> rmap) {
        List<Map<String, Object>> nlist = null;
        nlist = noticeDao.noticeList(rmap);
        return nlist;
    }
    public List<Map<String, Object>> noticeDetail(int n_no) {
        List<Map<String, Object>> nlist = null;
        int result = -1;
        nlist = noticeDao.noticeDetail(n_no);
        if(nlist != null){
            result = noticeDao.noticeHitCount(n_no);
        }
        return nlist;
    }
    public int noticeDelete(Map<String, Object> pmap) {
        int result = -1;
        result = noticeDao.noticeDelete(pmap);
        return result;
    }
    public int noticeUpdate(Map<String, Object> pmap) {
        int result = -1;
        result = noticeDao.noticeUpdate(pmap);
        return result;
    }
    public int fileUpload(List<Map<String, Object>> list){
        int result = -1;
        result = noticeDao.fileUpload(list);
        return result;
    }
    @Transactional
    public int noticeInsert(Map<String, Object> pmap) {
        int result = -1;
        if(pmap.containsKey("list")){//파일이 있는 경우 noticeInsert를 먼저하고 시퀀스 값을 받아옴
            List<Map<String, Object>> flist = (List)pmap.get("list");
            noticeDao.noticeInsert(pmap);
            for(Map<String, Object> map : flist){
                map.put("n_no", pmap.get("n_no"));
            }
            result = noticeDao.fileUpload(flist);
        }else{
            result = noticeDao.noticeInsert(pmap);//파일이 없는 경우 noticeInsert만
        }
        return result;
    }
}
