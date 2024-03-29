package com.sg.silvergarden.service.message;

import com.google.gson.Gson;
import com.sg.silvergarden.dao.message.MessageDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MessageService {
    @Autowired
    MessageDao messageDao;
    @Transactional
    public int messageSend(Map<String, Object> pmap) throws Exception{
        int result = -1;
        Gson g = new Gson();
        List<Map<String, Object>> receiverList = null;
        receiverList = g.fromJson(pmap.get("receiverList").toString(), List.class);//수신자명단을 받아옴
        if(pmap.containsKey("list")){//파일이 있는 경우
            List<Map<String, Object>> flist = (List)pmap.get("list");
            messageDao.messageSend(pmap);
            for(Map<String, Object> map : flist){
                map.put("me_no", pmap.get("me_no"));
            }
            for(Map<String, Object> map : receiverList){
                map.put("me_no", pmap.get("me_no"));
            }
            messageDao.messageRecipientInsert(receiverList);
            result = messageDao.messageFileUpload(flist);
        }else{
            messageDao.messageSend(pmap);//파일이 없는 경우
            for(Map<String, Object> map : receiverList){
                map.put("me_no", pmap.get("me_no"));
            }
            result = messageDao.messageRecipientInsert(receiverList);
        }
        return result;
    }

    public List<Map<String, Object>> messageReceiveList(Map<String, Object> rmap) throws Exception{
        List<Map<String, Object>> mList = null;
        mList = messageDao.messageReceiveList(rmap);
        return mList;
    }
    public List<Map<String, Object>> messageSendList(Map<String, Object> rmap) throws Exception{
        List<Map<String, Object>> mList = null;
        mList = messageDao.messageSendList(rmap);
        return mList;
    }

    public List<Map<String, Object>> messageDeletedList(Map<String, Object> rmap) throws Exception{
        List<Map<String, Object>> mList = null;
        mList = messageDao.messageDeletedList(rmap);
        return mList;
    }

    public List<Map<String, Object>> messageStoredList(Map<String, Object> rmap) throws Exception{
        List<Map<String, Object>> mList = null;
        mList = messageDao.messageStoredList(rmap);
        return mList;
    }

    public Map<String, Object> messageDetail(int me_no) throws Exception{
        Map<String, Object> meMap = null;
        meMap = messageDao.messageDetail(me_no);
        return meMap;
    }
    public int messageRead(Map<String, Object> rmap) throws Exception{
        int result = -1;
        result = messageDao.messageRead(rmap);
        return result;
    }
    public int messageStore(Map<String, Object> rmap) throws Exception{
        int result = -1;
        rmap.put("me_status","S");
        result = messageDao.messageStatusChange(rmap);
        return result;
    }

    public int messageDelete(Map<String, Object> rmap) throws Exception{
        int result = -1;
        rmap.put("me_status","N");
        result = messageDao.messageStatusChange(rmap);
        return result;
    }
    public int messageCompleteDelete(Map<String, Object> rmap) throws Exception{
        int result = -1;
        rmap.put("me_status","D");
        result = messageDao.messageStatusChange(rmap);
        return result;
    }

}
