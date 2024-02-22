package com.sg.silvergarden.service.empcreate;

import com.sg.silvergarden.dao.empcreate.SignupDao;
import com.sg.silvergarden.vo.empcreate.EmpVO;
import com.sg.silvergarden.vo.empcreate.SignupRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {

    private final SignupDao signupDao;
    private final PasswordEncoder passwordEncoder;
    @Override
    public EmpVO signup(SignupRequest signupRequest) {

        log.info("signup호출");
        log.info(signupRequest);
        EmpVO emp = new EmpVO();
        emp.setE_birth(signupRequest.getE_birth());
        emp.setE_name(signupRequest.getE_name());
        emp.setE_phone(signupRequest.getE_phone());
        emp.setDept_name(signupRequest.getDept_name());
        emp.setE_email(signupRequest.getE_email());
        emp.setE_auth(signupRequest.getE_auth());
        emp.setE_password(passwordEncoder.encode(signupRequest.getE_password()));
        return signupDao.save(emp);
    }

    @Override
    public List<Map<String, Object>> deptName(Map<String, Object> pmap) {
        log.info("deptName-service 호출");
        List<Map<String, Object>> list = new ArrayList<>();
        list = signupDao.deptname(pmap);
        return list;
    }
}
