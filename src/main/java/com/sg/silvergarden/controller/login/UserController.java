package com.sg.silvergarden.controller.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3000)
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/usera")
    public ResponseEntity<String> usera() {
        log.info("UseraController 호출");
        return ResponseEntity.ok("usera");

    }

    @GetMapping("/userb")
    public ResponseEntity<String> userb() {
        log.info("UserbController 호출");
        return ResponseEntity.ok("userb");

    }
}
