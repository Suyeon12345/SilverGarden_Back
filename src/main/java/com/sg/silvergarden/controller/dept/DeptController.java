package com.sg.silvergarden.controller.dept;

import com.google.gson.Gson;
import com.sg.silvergarden.service.dept.DeptService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/dept")
@CrossOrigin(origins = "http://localhost:3000/")
public class DeptController {

    @Autowired
    DeptService deptService = null;

    @GetMapping("deptlist")
    public String deptList(@RequestParam Map<String, Object> pmap){
        log.info("deptList-controller 호출");
        log.info(pmap);
        List<Map<String, Object>> list = null;
        list = deptService.deptList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(list);

        return temp;
    }

    @GetMapping("deptcheck")
    public int deptCheck(@RequestParam Map<String, Object> pmap){
        log.info("deptCheck-controller 호출");
        log.info(pmap);
        int result = deptService.deptCheck(pmap);
        log.info(result);

        return result;
    }

    @PostMapping("deptinsert")
    public void deptInsert(@RequestParam Map<String, Object> pmap){
        log.info("deptInsert-controller 호출");
        log.info(pmap);
        deptService.deptInsert(pmap);
    }

    @PutMapping("deptupdate")
    public void deptUpdate(@RequestParam Map<String, Object> pmap){
        log.info("deptUpdate-controller 호출");
        log.info(pmap);
        deptService.deptUpdate(pmap);
    }

    @PutMapping("deptdelete")
    public void deptDelete(@RequestParam Map<String, Object> pmap){
        log.info("deptDelete-controller 호출");
        log.info(pmap);
        deptService.deptDelete(pmap);
    }

    @GetMapping("joblist")
    public String jobList(@RequestParam Map<String, Object> pmap){
        log.info("jobList-controller 호출");
        log.info(pmap);
        List<Map<String, Object>> list = null;
        list = deptService.jobList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(list);

        return temp;
    }

    @PostMapping("jobinsert")
    public void jobInsert(@RequestParam Map<String, Object> pmap){
        log.info("jobInsert-controller 호출");
        log.info(pmap);
        deptService.jobInsert(pmap);
    }

    @PutMapping("jobdelete")
    public void jobDelete(@RequestParam Map<String, Object> pmap){
        log.info("jobDelete-controller 호출");
        log.info(pmap);
        deptService.jobDelete(pmap);
    }

    @GetMapping("emplist")
    public String empList(@RequestParam Map<String, Object> pmap){
        log.info("empList-controller 호출");
        log.info(pmap);
        List<Map<String, Object>> list = null;
        list = deptService.empList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(list);

        return temp;
    }



}
