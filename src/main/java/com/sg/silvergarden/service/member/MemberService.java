package com.sg.silvergarden.service.member;

import com.sg.silvergarden.dao.member.MemberDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MemberService{
    @Autowired
    MemberDao memberDao=null;
    public List<Map<String, Object>> memberList(Map<String, Object> mMap) {
        List<Map<String,Object>> mList = null;
        mList=memberDao.memberList(mMap);
        return mList;
    }

    public int memberDelete(Map<String, Object> mMap) {
            int result = 0;
            result = memberDao.memberDelete(mMap);
            return result;

    }
    public int memberUpdate(Map<String, Object> mMap) {
            int result = 0;
            result = memberDao.memberUpdate(mMap);
            return result;

    }
    public int memberInsert(Map<String, Object> mMap) {
        int result = 0;
            result = memberDao.memberInsert(mMap);
        return result;
    }

    public List<Map<String, Object>> counseList(Map<String, Object> cMap) {
        List<Map<String,Object>> cList = null;
        cList=memberDao.counselList(cMap);
        return cList;
    }

    public int counselDelete(Map<String, Object> cMap) {
        int result = 0;
        result = memberDao.counselDelete(cMap);
        return result;
    }

    public int counselUpdate(Map<String, Object> cMap) {
        int result = 0;
        result = memberDao.counselUpdate(cMap);
        return result;
    }

    public int counselInsert(Map<String, Object> cMap) {
        int result = 0;
        result = memberDao.counselInsert(cMap);
        return result;
    }

    public List<Map<String, Object>> shuttleList(Map<String, Object> sMap) {
        List<Map<String,Object>> sList = null;
        sList=memberDao.shuttleList(sMap);
        return sList;
    }

    public int shuttleDelete(Map<String, Object> sMap) {
        int result = 0;
        result = memberDao.shuttleDelete(sMap);
        return result;

    }
    public int shuttleUpdate(Map<String, Object> sMap) {
        int result = 0;
        result = memberDao.shuttleUpdate(sMap);
        return result;

    }
    public int shuttleInsert(Map<String, Object> sMap) {
        int result = 0;
        result = memberDao.shuttleInsert(sMap);
        return result;
    }

    public int ShuttleCalAdd(Map<String, Object> scMap) {
        return memberDao.ShuttleCalAdd(scMap);
    }

    public List<Map<String, Object>> shuttleCalList(Map<String, Object> scMap) {
        return memberDao.shttleCalList(scMap);
    }

    public int shuttleCalUpdate(Map<String, Object> scMap) {
        return memberDao.shttleCalUpdate(scMap);
    }

    public int shuttleCalDelete(Map<String, Object> scMap) {
        return memberDao.shttleCalDelete(scMap);
    }
}
