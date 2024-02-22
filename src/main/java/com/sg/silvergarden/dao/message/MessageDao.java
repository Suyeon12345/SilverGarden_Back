package com.sg.silvergarden.dao.message;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class MessageDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    public int messageFileUpload(List<Map<String, Object>> list) {
        int result = -1;
        log.info("fileUpload"+list.toString());
        result = sqlSessionTemplate.insert("messageFileUpload",list);
        return result;
    }
    public int messageSend(Map<String, Object> pmap){
        int result = -1;
        log.info("messageSend");
        result = sqlSessionTemplate.insert("messageSend",pmap);
        return result;
    }

    public int messageRecipientInsert(List<Map<String, Object>> receiverList){
        int result = -1;
        log.info("messageRecipientInsert");
        result = sqlSessionTemplate.insert("messageRecipientInsert",receiverList);
        return result;
    }

    public List<Map<String, Object>> messageReceiveList(String e_no) {
        List<Map<String, Object>> mList = null;
        mList = sqlSessionTemplate.selectList("messageReceiveList", e_no);
        return mList;
    }

    public List<Map<String, Object>> messageSendList(String e_no) {
        List<Map<String, Object>> mList = null;
        mList = sqlSessionTemplate.selectList("messageSendList", e_no);
        return mList;
    }

    public List<Map<String, Object>> messageStoredList(String e_no) {
        List<Map<String, Object>> mList = null;
        mList = sqlSessionTemplate.selectList("messageStoredList", e_no);
        return mList;
    }

    public List<Map<String, Object>> messageDeletedList(String e_no) {
        List<Map<String, Object>> mList = null;
        mList = sqlSessionTemplate.selectList("messageDeletedList", e_no);
        return mList;
    }

    public Map<String, Object> messageDetail(int me_no) {
        Map<String, Object> meMap = null;
        meMap = sqlSessionTemplate.selectOne("messageDetail",me_no);
        return meMap;
    }

    public int messageRead(Map<String, Object> rmap) {
        int result = -1;
        result = sqlSessionTemplate.update("messageRead",rmap);
        return result;
    }

    public int messageStatusChange(Map<String, Object> rmap) {
        int result = -1;
        result = sqlSessionTemplate.update("messageStatusChange",rmap);
        return result;
    }
}
