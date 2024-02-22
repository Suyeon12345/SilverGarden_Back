package com.sg.silvergarden.vo.login;

import com.sg.silvergarden.vo.empcreate.Role;
import lombok.Data;

@Data
public class JwtAuthentucationResponse {
    private String accessToken;
    private String refreshToken;
    private Role e_auth;
    private String e_name;
    private String dept_name;
    private String e_no;
    private String e_profile;
    private String e_rank;

}
