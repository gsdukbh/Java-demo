package top.werls.springsecurity.jwtdemo2.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author leejiawei
 */
@Component
@Slf4j
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "WERLS";
    private static final String CLAIM_KEY_CREATED = "admin";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.interval}")
    private Integer interval;

    /**
     * 生成token
     *
     * @param claims claims
     * @return token
     */
    private String generateToken(Map<String, Object> claims) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .setId(UUID.randomUUID().toString())
                .signWith(key)
                .compact();
    }

    /**
     * 生成过期时间
     *
     * @return 过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token 解析
     *
     * @param token token
     * @return 用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.get(CLAIM_KEY_USERNAME, String.class);
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * @param userDetails s
     * @return token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public String refreshHeadToken(String oldToken) {
        if (StringUtils.isEmpty(oldToken)) {
            return null;
        }
        String token = oldToken.substring(tokenHead.length());
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        //token校验不通过
        Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return null;
        }
        //如果token已经过期，不支持刷新
        if (isTokenExpired(token)) {
            return null;
        }
        //如果token在30分钟之内刚刷新过，返回原token
        if (tokenRefreshJustBefore(token, interval)) {
            return token;
        } else {
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateToken(claims);
        }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     *
     * @param token 原token
     * @param time  指定时间（秒）
     */
    private boolean tokenRefreshJustBefore(String token, int time) {
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Date refreshDate = new Date();
        //刷新时间在创建时间的指定时间内
        return refreshDate.after(created) && refreshDate.before(DateUtils.setMilliseconds(created, time));
    }
}
