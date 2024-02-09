package com.sg.silvergarden.controller.program;

import com.google.gson.Gson;
import com.sg.silvergarden.service.program.ProgramService;
import com.sg.silvergarden.vo.program.ProgramVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/angel/*")
public class ProgramController {
    Logger logger = LoggerFactory.getLogger(ProgramController.class);
    @Autowired
    ProgramService programService;

    //    전체 목록조회하기
    @GetMapping("pgList")
    public String pgList(@RequestParam Map<String, Object> pmap){
        logger.info("AngelController-pgList");
        List<Map<String, Object>> pgList = null;
        pgList = programService.pgList(pmap);
        if(pgList.size() == 0){
            logger.info("조회된 데이터가 없습니다.");
            return "noData";
        }
        logger.info(pgList.toString());
        Gson g = new Gson();
        String temp = g.toJson(pgList);
        return temp;
//        return "success";
    }
    //  프로그램 상세 조회하기
    @GetMapping("pgDetail")
    public String pgDetail(@RequestParam Map<String, Object> pmap){
        logger.info("AngelController-pgDetail");
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
        logger.info("AngelController-pgInsert");
        int result = 0;
        result = programService.pgInsert(pmap);
        return String.valueOf(result);
    }
    @DeleteMapping("pgDelete")
    public String pgDelete(int pg_no){
        logger.info("AngelController-pgDelete");
        int result = 0;
        result = programService.pgDelete(pg_no);
        return String.valueOf(result);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "pgUpdate")
    public String pgUpdate(@RequestBody Map<String, Object> pmap){
        logger.info("AngelController-pgUpdate");
        logger.info(pmap.toString());
        int result = 0;
        result = programService.pgUpdate(pmap);
        return String.valueOf(result);
    }

    @GetMapping("scheduleList")
    public String scheduleList(){
        logger.info("scheduleList");
        List<ProgramVO> calList = null;
        calList = programService.scheduleList();
        logger.info(calList.toString());
        Gson g = new Gson();
        String temp = g.toJson(calList);
        return temp;
    }
}
