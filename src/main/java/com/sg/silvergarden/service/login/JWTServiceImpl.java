package com.sg.silvergarden.service.login;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Log4j2
@Service
public class JWTServiceImpl implements JWTService{

    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        log.info("@@generateToken@@");
        return Jwts.builder().setSubject(userDetails.getUsername())//jwt생성하기 위한 빌더 시작 > jwt의 sub 클레임을 설정
                .setIssuedAt(new Date(System.currentTimeMillis()))//jwt 토큰 발급시간
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))//jwt 토큰 만료 시간
                .signWith(getSignKey(), SignatureAlgorithm.HS256)//jwt 서명추가
                .compact();//jwt 문자열로 변환하여 반환
    }

    @Override
    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        log.info("@@refreshToken@@");
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 604800000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private  <T> T extractClaim(String token, Function<Claims, T> claimsResolvers){
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }
    private Key getSignKey(){
        byte[] key = Decoders.BASE64.decode("aklfjklwejklaklklqkjfkljqpjfdklqasdkjfwijaklsjfaskdjfskl");
        return Keys.hmacShaKeyFor(key);

    }
    private Claims extractAllClaims(String token){
        return  Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpried(token));
    }

    private boolean isTokenExpried(String token){
        return extractClaim(token, Claims::getExpiration).before((new Date()));
    }


}
