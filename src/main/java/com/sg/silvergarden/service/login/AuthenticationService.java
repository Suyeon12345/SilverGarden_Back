package com.sg.silvergarden.service.login;

import com.sg.silvergarden.vo.login.JwtAuthentucationResponse;
import com.sg.silvergarden.vo.login.RefreshTokenRequest;
import com.sg.silvergarden.vo.login.SigninRequest;

public interface AuthenticationService {

    JwtAuthentucationResponse signin(SigninRequest signinRequest);

    JwtAuthentucationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
