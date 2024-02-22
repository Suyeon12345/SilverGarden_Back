package com.sg.silvergarden.controller.message;

import com.google.gson.Gson;
import com.sg.silvergarden.config.YAMLConfiguration;
import com.sg.silvergarden.service.message.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/message/*")
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    YAMLConfiguration config;

    @GetMapping("messageReceiveList")
    public String messageReceiveList(String e_no){
        log.info("messageReceiveList");
        List<Map<String, Object>> mList = null;
        mList = messageService.messageReceiveList(e_no);
        Gson g = new Gson();
        String temp = g.toJson(mList);
        log.info(temp);
        return temp;
    }

    @GetMapping("messageSendList")
    public String messageSendList(String e_no){
        log.info("messageSendList");
        List<Map<String, Object>> mList = null;
        mList = messageService.messageSendList(e_no);
        Gson g = new Gson();
        String temp = g.toJson(mList);
        log.info(temp);
        return temp;
    }

    @GetMapping("messageStoredList")
    public String messageStoredList(String e_no){
        log.info("messageStoredList");
        List<Map<String, Object>> mList = null;
        mList = messageService.messageStoredList(e_no);
        Gson g = new Gson();
        String temp = g.toJson(mList);
        log.info(temp);
        return temp;
    }

    @GetMapping("messageDeletedList")
    public String messageDeletedList(String e_no){
        log.info("messageDeletedList");
        List<Map<String, Object>> mList = null;
        mList = messageService.messageDeletedList(e_no);
        Gson g = new Gson();
        String temp = g.toJson(mList);
        log.info(temp);
        return temp;
    }

    @GetMapping("messageDetail")
    public String messageDetail(int me_no){
        log.info("messageDetail");
        Map<String, Object> meMap = null;
        meMap = messageService.messageDetail(me_no);
        Gson g = new Gson();
        String temp = g.toJson(meMap);
        return temp;
    }

    @PostMapping("messageSend")
    public String messageSend(@RequestParam Map<String, Object> pmap, @RequestParam(name="files", required = false) MultipartFile[] files ){
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
                    nmap.put("send_id", pmap.get("send_id"));
                    nmap.put("me_filepath", config.getUploadPath());
                    nmap.put("me_fileorigin", originalFilename);
                    nmap.put("me_filename", uploadFilename);
                    list.add(nmap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            pmap.put("list", list);//맵에 파일리스트를 추가해줌
        }
        log.info(pmap.toString());
        int result = -1;
        result = messageService.messageSend(pmap);
        return result == 0?"error":"ok";
    }

    @GetMapping("messageRead")
    public String messageRead(@RequestParam Map<String, Object> rmap){
        log.info(rmap.toString());
        int result = -1;
        result = messageService.messageRead(rmap);
        return result == 0?"error":"ok";
    }
    @GetMapping("messageStore")
    public String messageStore(@RequestParam Map<String, Object> rmap){
        log.info(rmap.toString());
        int result = -1;
        result = messageService.messageStore(rmap);
        log.info(String.valueOf(result));
        return result == 0?"error":"ok";
    }
    @GetMapping("messageDelete")
    public String messageDelete(@RequestParam Map<String, Object> rmap){
        log.info(rmap.toString());
        int result = -1;
        result = messageService.messageDelete(rmap);
        return result == 0?"error":"ok";
    }

    @GetMapping("messageFileDownload")
    public ResponseEntity<Object> fileDownload(@RequestParam(value="filename") String filename) {
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
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 다운로드 오류");
        }
    }
    private String getCurrentTimeMillisFormat() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(new Date(currentTime));
    }
}
