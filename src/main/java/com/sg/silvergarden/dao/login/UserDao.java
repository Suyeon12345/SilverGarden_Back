package com.sg.silvergarden.dao.login;

import com.sg.silvergarden.vo.empcreate.EmpVO;
import com.sg.silvergarden.vo.empcreate.Role;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface UserDao {
    Optional<EmpVO> findById(@Param("e_no") String e_no);

    EmpVO findByRole(@Param("e_auth") Role e_auth);

}
