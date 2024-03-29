package com.sg.silvergarden.controller.notice;

import com.google.gson.Gson;
import com.sg.silvergarden.config.YAMLConfiguration;
import com.sg.silvergarden.service.notice.NoticeService;
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
@RequestMapping("/notice/*")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @Autowired
    YAMLConfiguration config;

    @GetMapping("noticeList")
    public String noticeList(@RequestParam Map<String, Object> rmap) throws Exception{
        long startTime = System.currentTimeMillis(); // 요청 시작 시간 측정
        log.info("noticeList");
        log.info(rmap.toString());
        List<Map<String, Object>> nlist = noticeService.noticeList(rmap);
        Gson g = new Gson();
        String temp = g.toJson(nlist);
        long endTime = System.currentTimeMillis(); // 요청 종료 시간 측정
        long duration = endTime - startTime; // 처리 시간 계산
        log.info("API 요청부터 반환까지의 시간: " + duration + " ms"); // 로그에 시간 출력
        return temp;
    }
    @GetMapping("noticeTotalCount")
    public String noticeTotalCount(@RequestParam Map<String, Object> rmap) throws Exception{
        Map<String, Object> count = noticeService.noticeTotalCount(rmap);
        Gson g = new Gson();
        String temp = g.toJson(count);
        log.info(temp);
        return temp;
    }

    @GetMapping("noticeDetail")
    public String noticeDetail(int n_no) throws Exception{
        log.info("noticeDetail");
        if(ObjectUtils.isEmpty(n_no)){
            return "잘못된 요청입니다";
        }
        log.info(String.valueOf(n_no));
        List<Map<String, Object>> nlist = null;
        nlist = noticeService.noticeDetail(n_no);
        Gson g = new Gson();
        String temp = g.toJson(nlist);
        return temp;
    }
    @PostMapping("noticeInsert")
    public String noticeInsert(@RequestParam Map<String, Object> pmap, @RequestParam(name="files", required = false) MultipartFile[] files) throws Exception{
        if(ObjectUtils.isEmpty(pmap)){
            return "잘못된 요청입니다";
        }
        log.info(pmap.toString());
        List<Map<String, Object>> list = new ArrayList<>();
        if(files != null){//파일이 있는 경우
            for(MultipartFile file : files){
                Map<String, Object> nmap = new HashMap<>();
                String originalFilename = file.getOriginalFilename();
                String uploadFilename = getCurrentTimeMillisFormat() + "_" + FilenameUtils.getName(originalFilename);
                File upFile = new File(config.getUploadPath(), uploadFilename);//지정된 경로에 파일저장
                try {
                    file.transferTo(upFile);
                    nmap.put("e_no", pmap.get("e_no"));
                    nmap.put("n_filepath", config.getUploadPath());
                    nmap.put("n_fileorigin", originalFilename);
                    nmap.put("n_filename", uploadFilename);
                    list.add(nmap);
                } catch (IOException e) {
                    throw new IOException(e);
                }
            }
            pmap.put("list", list);//맵에 파일리스트를 추가해줌
        }
        log.info(pmap.toString());
        int result = -1;
        result = noticeService.noticeInsert(pmap);
        return result == 0?"error":"ok";
    }

    @GetMapping("noticeDelete")
    public String noticeDelete(@RequestParam Map<String, Object> pmap) throws Exception{
        if(ObjectUtils.isEmpty(pmap)){
            return "잘못된 요청입니다";
        }
        log.info(pmap.toString());
        int result = -1;
        result = noticeService.noticeDelete(pmap);
        return result == 0?"error":"ok";
    }

    @GetMapping("noticeUpdate")
    public String noticeUpdate(@RequestParam Map<String, Object> pmap) throws Exception{
        if(ObjectUtils.isEmpty(pmap)){
            return "잘못된 요청입니다";
        }
        log.info(pmap.toString());
        int result = -1;
        result = noticeService.noticeUpdate(pmap);
        return result == 0?"error":"ok";
    }

    private String getCurrentTimeMillisFormat() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(new Date(currentTime));
    }
    @PostMapping("imageUpload")
    public String imageUpload(@RequestParam(value = "image") MultipartFile image) throws Exception{
        log.info("이미지 업로드");
        if(ObjectUtils.isEmpty(image)){
            return "잘못된 요청입니다";
        }
        String newFilename = getCurrentTimeMillisFormat()+"_"+FilenameUtils.getName(image.getOriginalFilename());
        File upImage = new File(config.getUploadPath(), newFilename);
        log.info(newFilename);
        try {
            image.transferTo(upImage);
            return newFilename;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("fileDownload")
    public ResponseEntity<Object> fileDownload(@RequestParam(value="filename") String filename) throws Exception{
        log.info("fileDownload 호출 성공");
        if(ObjectUtils.isEmpty(filename)){
            return ResponseEntity.badRequest().body("잘못된 요청");
        }
        log.info(filename);
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
            throw e;
        }
    }// end of fileDownLoad
    //파일 삭제 처리
    @PostMapping("deleteFile")
    public ResponseEntity<String> deleteFile(String filename) throws Exception{
        if(ObjectUtils.isEmpty(filename)){
            return ResponseEntity.badRequest().body("잘못된 요청");
        }
        File file = null;
        if(filename != null){
            file = new File(config.getUploadPath() + filename);
        }
        if(file.delete() == true){
            int result = -1;
            result = noticeService.deleteFile(filename);
            log.info(String.valueOf(result));
            return new ResponseEntity<>("삭제성공!!!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
