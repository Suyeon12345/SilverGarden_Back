package com.sg.silvergarden.controller.mypage;


import com.google.gson.Gson;
import com.sg.silvergarden.service.mypage.MypageService;
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
@RequestMapping("/my/*")
public class MypageController {
    @Autowired
    MypageService mypageService=null;
    @GetMapping("mypage")
    public String callMypage(@RequestParam Map<String, Object> mMap) {
        log.info("mypage");
        log.info(mMap.toString());
        List<Map<String, Object>> mList = null;
        mList = mypageService.callMypage(mMap);
        Gson g = new Gson();
        String temp = g.toJson(mList);
        return temp;
    }
}
