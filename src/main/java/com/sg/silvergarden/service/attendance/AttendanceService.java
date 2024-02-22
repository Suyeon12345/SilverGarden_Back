package com.sg.silvergarden.service.attendance;

import com.sg.silvergarden.dao.attendance.AttendanceDao;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class AttendanceService {
    Logger logger = LoggerFactory.getLogger(AttendanceService.class);
    @Autowired
    private AttendanceDao attendanceDao;

    public List<Map<String, Object>> atList(Map<String, Object> atMap) {
        return attendanceDao.atList(atMap);
    }

    public int atInsert(Map<String, Object> atMap) {
        return attendanceDao.atInsert(atMap);
    }

    public int atUpdate(Map<String, Object> atMap) {
        return attendanceDao.atUpdate(atMap);
    }

    public int adminAtUpdate(Map<String, Object> atMap) {
        return attendanceDao.adminAtUpdate(atMap);
    }

    public void noneAtInsert() {
        attendanceDao.noneAtInsert();
    }
}
