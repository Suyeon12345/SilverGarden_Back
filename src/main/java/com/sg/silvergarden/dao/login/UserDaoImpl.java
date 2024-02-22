package com.sg.silvergarden.dao.login;


import com.sg.silvergarden.vo.empcreate.EmpVO;
import com.sg.silvergarden.vo.empcreate.Role;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UserDaoImpl implements UserDao {


    @Autowired
    SqlSessionTemplate sqlSessionTemplate = null;
    @Override
    public Optional<EmpVO> findById(String e_no) {

        log.info("findById호출");
        log.info(e_no);
        List<EmpVO> mVO = sqlSessionTemplate.selectList("loginMapper.findById", e_no);
        log.info("@@@");
        log.info(mVO);
        log.info(Optional.of(mVO.get(0)));
        return Optional.of(mVO.get(0));
    }

    @Override
    public EmpVO findByRole(Role e_auth) {
        log.info("findByRole호출");
        return sqlSessionTemplate.selectOne("loginMapper.findByRole", e_auth);
    }
}
