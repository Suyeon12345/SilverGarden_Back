package com.sg.silvergarden.controller.empcreate;

import com.google.gson.Gson;
import com.sg.silvergarden.service.empcreate.SignupService;
import com.sg.silvergarden.vo.empcreate.EmpVO;
import com.sg.silvergarden.vo.empcreate.SignupRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class SignupController {

    private final SignupService signupService;
    @PostMapping("/signup")
    public ResponseEntity<EmpVO> signup(@RequestBody SignupRequest signUpRequest){
        log.info("signup-controller");
        return ResponseEntity.ok(signupService.signup(signUpRequest));
    }

    @GetMapping("/deptname")
    public String deptName(Map<String, Object> pmap){
        log.info("empcreate -> deptname-controller");
        List<Map<String, Object>> list = null;
        list = signupService.deptName(pmap);
        Gson g = new Gson();
        String temp = g.toJson(list);
        log.info(temp);
        return temp;


    }


}
