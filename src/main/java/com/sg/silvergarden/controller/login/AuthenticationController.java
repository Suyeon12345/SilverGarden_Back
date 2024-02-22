package com.sg.silvergarden.controller.login;

import com.sg.silvergarden.service.login.AuthenticationService;
import com.sg.silvergarden.vo.login.JwtAuthentucationResponse;
import com.sg.silvergarden.vo.login.RefreshTokenRequest;
import com.sg.silvergarden.vo.login.SigninRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthentucationResponse> signin(@RequestBody SigninRequest signinRequest) {
        log.info(authenticationService.signin(signinRequest));
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthentucationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
