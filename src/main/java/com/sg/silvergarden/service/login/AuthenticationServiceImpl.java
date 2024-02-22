package com.sg.silvergarden.service.login;

import com.sg.silvergarden.dao.login.UserDao;
import com.sg.silvergarden.vo.empcreate.EmpVO;
import com.sg.silvergarden.vo.login.JwtAuthentucationResponse;
import com.sg.silvergarden.vo.login.RefreshTokenRequest;
import com.sg.silvergarden.vo.login.SigninRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserDao userDao;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthentucationResponse signin(SigninRequest signinRequest) {
        log.info("signin호출");
        log.info(signinRequest);
        log.info(signinRequest.getE_no());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getE_no(),
                signinRequest.getE_password()));
        var user = userDao.findById(signinRequest.getE_no())
                .orElseThrow(() -> new IllegalArgumentException("invalid id or password"));
        log.info(user);
        var role = user.getE_auth();
        var profile = user.getE_profile();
        var dept = user.getDept_name();
        var name = user.getE_name();
        var no = user.getE_no();
        var rank = user.getE_rank();
        var jwt = jwtService.generateToken(user);
        log.info(jwt);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        log.info(refreshToken);

        JwtAuthentucationResponse jwtAuthentucationResponse = new JwtAuthentucationResponse();

        jwtAuthentucationResponse.setAccessToken(jwt);
        jwtAuthentucationResponse.setRefreshToken(refreshToken);
        jwtAuthentucationResponse.setE_auth(role);
        jwtAuthentucationResponse.setE_profile(profile);
        jwtAuthentucationResponse.setE_no(no);
        jwtAuthentucationResponse.setDept_name(dept);
        jwtAuthentucationResponse.setE_name(name);
        jwtAuthentucationResponse.setE_rank(rank);

        return jwtAuthentucationResponse;

    }

    @Override
    public JwtAuthentucationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

        log.info("refresh 호출");
        String userID = jwtService.extractUserName(refreshTokenRequest.getToken());
        EmpVO user = userDao.findById(userID).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthentucationResponse jwtAuthentucationResponse = new JwtAuthentucationResponse();

            jwtAuthentucationResponse.setAccessToken(jwt);
            jwtAuthentucationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthentucationResponse;
        }

        return null;
    }
}
