package com.sg.silvergarden.vo.empcreate;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Log4j2
@Data
@NoArgsConstructor
public class EmpVO implements UserDetails {

    private String e_no;
    private String e_birth;
    private String e_name;
    private String e_phone;
    private String dept_name;
    private String e_email;
    private String e_password;
    private String e_profile;
    private Role e_auth;
    private String e_rank;

    @Builder
    public EmpVO(String e_no, String e_birth, String e_name, String e_phone, String dept_name, String e_email,
            String e_password, Role e_auth, String e_profile, String e_rank) {
        super();
        this.e_no = e_no;
        this.e_birth = e_birth;
        this.e_name = e_name;
        this.e_phone = e_phone;
        this.dept_name = dept_name;
        this.e_email = e_email;
        this.e_password = e_password;
        this.e_auth = e_auth;
        this.e_profile = e_profile;
        this.e_rank = e_rank;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(e_auth.name()));
    }

    @Override
    public String getPassword() {
        return e_password;
    }

    @Override
    public String getUsername() {
        return e_no;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
