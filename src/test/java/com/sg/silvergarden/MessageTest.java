package com.sg.silvergarden;

import com.sg.silvergarden.dao.message.MessageDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MessageTest {
    @Autowired
    MessageDao messageDao;

    @Test
    public void messageRecipientInsertTest(){
        List<Map<String, Object>> receiverList = new ArrayList<>();
        Map<String, Object> rmap = new HashMap<>();
        rmap.put("me_no", 1);
        rmap.put("r_id", 1);
        receiverList.add(rmap);
        rmap = new HashMap<>();
        rmap.put("me_no", 1);
        rmap.put("r_id", 2);
        receiverList.add(rmap);
        rmap = new HashMap<>();
        rmap.put("me_no", 1);
        rmap.put("r_id", 3);
        receiverList.add(rmap);
        assertEquals(messageDao.messageRecipientInsert(receiverList), 3);
    }
    @Test
    public void messsageReceiveList(){
        String e_no = "202402_00000036";
        assertEquals(messageDao.messageReceiveList(e_no).size(), 5);
    }
    @Test
    public void messageSendList(){
        String e_no = "202402_00000036";
        assertEquals(messageDao.messageSendList(e_no).size(), 5);
    }
}
