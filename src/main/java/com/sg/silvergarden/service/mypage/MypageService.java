package com.sg.silvergarden.service.mypage;

import com.sg.silvergarden.dao.member.MemberDao;
import com.sg.silvergarden.dao.mypage.MypageDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MypageService {
    @Autowired
    private MypageDao mypageDao;
    public List<Map<String, Object>> callMypage(Map<String, Object> mMap) {
        List<Map<String,Object>> mList = null;
        mList= mypageDao.callMypage(mMap);
        return mList;
    }


}
