package com.leetcodeClone.user_service.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtTokenUtil {
    private static final String SECRET_KEY = "374d6153fb0c12077a8d87505ab56d69ec0bdf9bb24b6ccfcb211bd6863fb016f8f31d0efd8da31dc131ca7c541a8195e45f967ff351b811e25a0c6b0746989d75148daa834890fdf8817c394c8e0d9d70bea336e5b4d3654058d3e1d298f4de43743a346f7d1c44b7c26f084bd4e363fcf6a70211795b294ce68b05abe16fe2aa9d6c91b4f14199f4ffe8c9b748ab1125f50613d234793e69aa0f3cfbdf89e8f765a9935dd6d7da9e61224ab6eb96bce5a41123c017d11eb337c3a7c6615f831b73363765df7f3480df99427fecd8071535b037c02d076f32b1b013b5c32358192890e81277b477a7ee8ef174fb350dfd622172662530092040daa4bca78137";

    private static final long EXPIRATION_TIME = 3600000;

    public String generateToken(String email){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public String getEmailFromToken(String token){
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
