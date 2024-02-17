package com.sg.silvergarden.controller.program;

import com.google.gson.Gson;
import com.sg.silvergarden.service.program.ProgramService2;
import com.sg.silvergarden.vo.programcal.ProgramSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/program/*")
public class ProgramController2 {
    Logger logger = LoggerFactory.getLogger(ProgramController2.class);
    @Autowired
    ProgramService2 programService;

    //    전체 목록조회하기
    @GetMapping("pgList")
    public String pgList(@RequestParam Map<String, Object> pmap){
        logger.info("ProgramController2-pgList");
        List<Map<String, Object>> pgList = null;
        pgList = programService.pgList(pmap);
        if(pgList.size() == 0){
            logger.info("조회된 데이터가 없습니다.");
            return "noData";
        }
//        logger.info(pgList.toString());
        Gson g = new Gson();
        String temp = g.toJson(pgList);
        return temp;
//        return "success";
    }
    //  프로그램 상세 조회하기
    @GetMapping("pgDetail")
    public String pgDetail(@RequestParam Map<String, Object> pmap){
        logger.info("ProgramController2-pgDetail");
        logger.info("pmap: {}", pmap);
        List<Map<String, Object>> pgList = null;
        pgList = programService.pgList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(pgList);
        System.out.println(temp);
        return temp;
    }

    //    프로그램 등록하기
    @PostMapping("pgInsert")
    public String pgInsert(@RequestBody Map<String, Object> pmap){
        logger.info("ProgramController2-pgInsert");
        logger.info("pmap: ", pmap);
        int result = -1;
        result = programService.pgInsert(pmap);
        if (result == 1) {
            // 프로그램 등록이 성공했을 때만 pgCalInsert 호출
            int calInsertResult = programService.pgCalInsert(pmap);
            logger.info("pgCalInsert result: {}", calInsertResult);
        }
        logger.info("error");
        return String.valueOf(result);
    }
    @GetMapping("pgDelete")
    public String pgDelete(@RequestParam Map<String, Object> pmap){
        logger.info("ProgramController2-pgDelete");
        int result = 0;
        result = programService.pgDelete(pmap);
        return String.valueOf(result);
    }
    @GetMapping(value = "pgUpdate")
    public String pgUpdate(@RequestParam Map<String, Object> pmap){
        logger.info("ProgramController2-pgUpdate");
        logger.info("pmap: {}", pmap);
        int result = 0;
        result = programService.pgUpdate(pmap);
        return String.valueOf(result);
    }

    @GetMapping("scheduleList")
    public String scheduleList(){
        logger.info("ProgramController2-scheduleList");
        List<ProgramSchedule> calList = null;
        calList = programService.scheduleList();
        logger.info(calList.toString());
        Gson g = new Gson();
        String temp = g.toJson(calList);
        return temp;
    }
}