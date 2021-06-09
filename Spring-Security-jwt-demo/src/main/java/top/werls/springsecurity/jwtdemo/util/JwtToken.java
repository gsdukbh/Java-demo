package top.werls.springsecurity.jwtdemo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;


/**
 * @author leejiawei
 */
@Data
@Component
public class JwtToken {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.tokenHeader}")
    private String tokenHead;
    @Value("${jwt.iss}")
    private String issuer;
    @Value("${jwt.aud}")
    private String audience;
    @Value("${jwt.prefix}")
    private String prefix;
    @Value("${jwt.expiration}")
    private Long expiration;

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

}
