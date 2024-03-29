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
    public int messageFileUpload(List<Map<String, Object>> list) throws Exception{
        int result = -1;
        log.info("fileUpload"+list.toString());
        result = sqlSessionTemplate.insert("messageFileUpload",list);
        return result;
    }
    public int messageSend(Map<String, Object> pmap) throws Exception{
        int result = -1;
        log.info("messageSend");
        result = sqlSessionTemplate.insert("messageSend",pmap);
        return result;
    }

    public int messageRecipientInsert(List<Map<String, Object>> receiverList) throws Exception{
        int result = -1;
        log.info("messageRecipientInsert");
        result = sqlSessionTemplate.insert("messageRecipientInsert",receiverList);
        return result;
    }

    public List<Map<String, Object>> messageReceiveList(Map<String, Object> rmap) throws Exception{
        List<Map<String, Object>> mList = null;
        mList = sqlSessionTemplate.selectList("messageReceiveList", rmap);
        return mList;
    }

    public List<Map<String, Object>> messageSendList(Map<String, Object> rmap) throws Exception{
        List<Map<String, Object>> mList = null;
        mList = sqlSessionTemplate.selectList("messageSendList", rmap);
        return mList;
    }

    public List<Map<String, Object>> messageStoredList(Map<String, Object> rmap) throws Exception{
        List<Map<String, Object>> mList = null;
        mList = sqlSessionTemplate.selectList("messageStoredList", rmap);
        return mList;
    }

    public List<Map<String, Object>> messageDeletedList(Map<String, Object> rmap) throws Exception{
        List<Map<String, Object>> mList = null;
        mList = sqlSessionTemplate.selectList("messageDeletedList", rmap);
        return mList;
    }

    public Map<String, Object> messageDetail(int me_no) throws Exception{
        Map<String, Object> meMap = null;
        meMap = sqlSessionTemplate.selectOne("messageDetail",me_no);
        return meMap;
    }

    public int messageRead(Map<String, Object> rmap) throws Exception{
        int result = -1;
        result = sqlSessionTemplate.update("messageRead",rmap);
        return result;
    }

    public int messageStatusChange(Map<String, Object> rmap) throws Exception{
        int result = -1;
        result = sqlSessionTemplate.update("messageStatusChange",rmap);
        return result;
    }
}
