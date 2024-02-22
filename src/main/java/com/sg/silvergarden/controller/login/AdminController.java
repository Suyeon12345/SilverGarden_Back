package com.sg.silvergarden.controller.login;

import com.sg.silvergarden.dao.login.UserDao;
import com.sg.silvergarden.vo.empcreate.EmpVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@CrossOrigin(origins = "*", maxAge = 3000)
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/admin")
    public ResponseEntity<String> admin()
    {
        log.info("AdminController 호출");
        return ResponseEntity.ok("admin");

    }
}
