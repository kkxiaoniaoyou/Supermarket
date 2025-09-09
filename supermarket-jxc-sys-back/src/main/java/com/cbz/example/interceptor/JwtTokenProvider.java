package com.cbz.example.interceptor;

/**
 * @author 11272
 * @description:
 * @date 2024/1/10 15:54
 **/

import com.cbz.example.exception.BusinessException;
import com.cbz.example.exception.enums.BusinessExceptionEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long validityInMilliseconds;

    /**
     * 使用 KeyResolver 解析密钥
     */
    private Key keyResolver() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /**
     * 生成 Token
     *
     * @param username
     * @return
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(keyResolver(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析 Token
     *
     * @param token
     * @return
     */
    public String getUserFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(keyResolver())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            throw new BusinessException(BusinessExceptionEnum.TOKEN_EXPIRED.getCode(), BusinessExceptionEnum.TOKEN_EXPIRED.getMessage());
        } catch (MalformedJwtException e) {
            throw new BusinessException(BusinessExceptionEnum.TOKEN_FORMAT_ERROR.getCode(), BusinessExceptionEnum.TOKEN_FORMAT_ERROR.getMessage());
        }
    }

    /**
     * 验证 Token 是否过期
     *
     * @param token
     * @return
     */
    public boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(keyResolver())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return !claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            throw new BusinessException(BusinessExceptionEnum.TOKEN_EXPIRED.getCode(), BusinessExceptionEnum.TOKEN_EXPIRED.getMessage());
        } catch (MalformedJwtException e) {
            throw new BusinessException(BusinessExceptionEnum.TOKEN_FORMAT_ERROR.getCode(), BusinessExceptionEnum.TOKEN_FORMAT_ERROR.getMessage());
        }
    }
}


