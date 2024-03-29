package com.sg.silvergarden.controller.approval;

import com.google.gson.Gson;
import com.sg.silvergarden.config.YAMLConfiguration;
import com.sg.silvergarden.service.approval.ApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/approval/*")
public class ApprovalController {

    @Autowired
    YAMLConfiguration config;

    @Autowired
    ApprovalService approvalService;
    @GetMapping("allApprovalList")
    public String allApprovalList(@RequestParam Map<String, Object> pmap) throws Exception{
        log.info("allApprovalList");
        List<Map<String, Object>> dList = null;
        dList = approvalService.allApprovalList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }

    @GetMapping("approvalWaitList")
    public String approvalWaitList(@RequestParam Map<String, Object> pmap) throws Exception{
        log.info("approvalWaitList");
        if(ObjectUtils.isEmpty(pmap)){//요청파라미터가 null일 때 방어하는 코드
            return "잘못된 요청입니다.";
        }
        List<Map<String, Object>> dList = null;
        dList = approvalService.approvalWaitList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }

    @GetMapping("approvalCompleteList")
    public String approvalCompleteList(@RequestParam Map<String, Object> pmap) throws Exception{
        log.info("approvalCompleteList");
        if(ObjectUtils.isEmpty(pmap)){//요청파라미터가 null일 때 방어하는 코드
            return "잘못된 요청입니다.";
        }
        List<Map<String, Object>> dList = null;
        dList = approvalService.approvalCompleteList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }

    @GetMapping("approvalDenyList")
    public String approvalDenyList(@RequestParam Map<String, Object> pmap) throws Exception{
        log.info("approvalDenyList");
        if(ObjectUtils.isEmpty(pmap)){//요청파라미터가 null일 때 방어하는 코드
            return "잘못된 요청입니다.";
        }
        List<Map<String, Object>> dList = null;
        dList = approvalService.approvalDenyList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }

    @GetMapping("approvalProgressList")
    public String approvalProgressList(@RequestParam Map<String, Object> pmap) throws Exception{
        log.info("approvalProgressList");
        if(ObjectUtils.isEmpty(pmap)){
            return "잘못된 요청입니다.";
        }
        List<Map<String, Object>> dList = null;
        dList = approvalService.approvalProgressList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }
    @GetMapping("approvalTempList")
    public String approvalTempList(@RequestParam Map<String, Object> pmap) throws Exception{
        log.info("approvalTempList");
        if(ObjectUtils.isEmpty(pmap)){//요청파라미터가 null일 때 방어하는 코드
            return "잘못된 요청입니다.";
        }
        List<Map<String, Object>> dList = null;
        dList = approvalService.approvalTempList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }

    @GetMapping("getDeptData")
    public String getDeptDate() throws Exception{
        log.info("getDeptData");
        List<Map<String, Object>> dList = null;
        dList = approvalService.getDeptData();
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }
    @GetMapping("getApprovalDocCount")
    public String getApprovalDocCount(String e_no) throws Exception{
        log.info("ApprovalController: getApprovalDocCount");
        if(ObjectUtils.isEmpty(e_no)){//요청파라미터가 null일 때 방어하는 코드
            return "잘못된 요청입니다";
        }
        Map<String, Object> rmap = null;
        rmap = approvalService.getApprovalDocCount(e_no);
        Gson g = new Gson();
        String temp = g.toJson(rmap);
        return temp;
    }

    @GetMapping("getApprovalDetail")
    public String getApprovalDetail(int d_no) throws Exception{
        log.info("getApprovalDetail");
        log.info(String.valueOf(d_no));
        if(ObjectUtils.isEmpty(d_no)){//요청파라미터가 null일 때 방어하는 코드
            return "잘못된 요청입니다";
        }
        List<Map<String, Object>> dList = null;
        dList = approvalService.getApprovalDetail(d_no);
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }

    @PostMapping("approvalInsert")
    public String approvalInsert(@RequestParam Map<String, Object> pmap, @RequestParam(name="files", required = false) MultipartFile[] files) throws Exception{
        int result = -1;
        if(ObjectUtils.isEmpty(pmap)){
            return "잘못된 요청입니다";
        }
        List<Map<String, Object>> fileList = new ArrayList<>();
        if(files != null){//파일이 있는 경우
            for(MultipartFile file : files){
                Map<String, Object> dmap = new HashMap<>();
                String originalFilename = file.getOriginalFilename();
                String uploadFilename = getCurrentTimeMillisFormat() + "_" + FilenameUtils.getName(originalFilename);
                File upFile = new File(config.getUploadPath(), uploadFilename);//지정된 경로에 파일저장
                try {
                    file.transferTo(upFile);
                    dmap.put("e_no", pmap.get("e_no"));
                    dmap.put("ap_filepath", config.getUploadPath());
                    dmap.put("ap_fileorigin", originalFilename);
                    dmap.put("ap_filename", uploadFilename);
                    fileList.add(dmap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            pmap.put("fileList", fileList);//맵에 파일리스트를 추가해줌
        }
        result = approvalService.approvalInsert(pmap);
        return result == 0 || result == -1 ?"error":"ok";//결과값이 -1 혹은 0이면 에러를 반환
    }

    @GetMapping("approvalFileDownload")
    public ResponseEntity<Object> fileDownload(@RequestParam(value="filename") String filename) throws Exception{
        log.info("fileDownload 호출 성공");
        log.info(filename);
        if(ObjectUtils.isEmpty(filename)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청");
        }
        try {
            String encodedFilename = URLEncoder.encode(filename, "UTF-8").replace("+", "%20");
            File file = new File(config.getUploadPath(), URLDecoder.decode(encodedFilename, "UTF-8"));

            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+encodedFilename);
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");

            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = null;
            resource = new ByteArrayResource(Files.readAllBytes(path));

            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 다운로드 오류");
        }
    }

    @PutMapping("passOrDeny")
    public String passOrDeny(@RequestBody Map<String, Object> pmap) throws Exception{
        log.info("passOrDeny");
        log.info(pmap.toString());
        if(ObjectUtils.isEmpty(pmap)){
            return "잘못된 요청입니다";
        }
        int result = -1;
        result=approvalService.passOrDeny(pmap);
        log.info(String.valueOf(result));
        return result == 0 || result == -1 ?"error":"ok";
    }

    @DeleteMapping("approvalDelete")
    public String approvalDelete(int d_no) throws Exception{
        log.info("approvalDelete");
        if(ObjectUtils.isEmpty(d_no)){
            return "잘못된 요청입니다";
        }
        int result = -1;
        result=approvalService.approvalDelete(d_no);
        log.info(String.valueOf(result));
        return result == 0 || result == -1 ?"error":"ok";
    }

    @GetMapping("approvalWithdrawal")
    public String approvalWithdrawal(int d_no) throws Exception{
        log.info("approvalWithdrawal");
        if(ObjectUtils.isEmpty(d_no)){
            return "잘못된 요청입니다";
        }
        int result = -1;
        result=approvalService.approvalWithdrawal(d_no);
        log.info(String.valueOf(result));
        return result == 0 || result == -1 ?"error":"ok";
    }

    private String getCurrentTimeMillisFormat() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(new Date(currentTime));
    }
}
