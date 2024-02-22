package com.sg.silvergarden;

import com.sg.silvergarden.dao.approval.ApprovalDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ApprovalTest {
    @Autowired
    ApprovalDao approvalDao;

    @Test
    public void getFinalApprovalLevelTest(){
        int d_no = 43;
        assertEquals(approvalDao.getFinalApprovalLevel(d_no), 3);
    }

}
