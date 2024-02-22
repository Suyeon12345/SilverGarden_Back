package com.sg.silvergarden.controller.emplist;

import com.google.gson.Gson;
import com.sg.silvergarden.service.emplist.EmpListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/emplist/*")
public class EmpListController {
    @Autowired
    EmpListService empListService=null;
    @GetMapping("all")
    public String allEmpList(@RequestParam Map<String,Object> eMap){
        log.info("empList");
        List<Map<String, Object>> eList = null;
        eList = empListService.allEmpList(eMap);
        Gson g = new Gson();
        String temp = g.toJson(eList);
        return temp;
    }
}
