package io.wisoft.leedongyeop.jwtpractice.jwt;

import io.jsonwebtoken.*;

import io.wisoft.leedongyeop.jwtpractice.config.WebMvcConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private String secretKey;
    private long validityInMilliseconds;
    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    public JwtTokenProvider(
            @Value("${security.jwt.token.secret-key}") String secretKey,
            @Value("${security.jwt.token.expire-length}") long validityInMilliseconds) {

        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.validityInMilliseconds = validityInMilliseconds;
    }

    /** 토큰 생성 */
    public String createToken(final String subject) {
        Claims claims = Jwts.claims().setSubject(subject);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        logger.info("now: {}", now);
        logger.info("validity: {}", validity);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    /** 토큰에서 값 추출 */
    public String getSubject(final String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    /** 유효한 토큰인지 확인 */
    public boolean validateToken(final String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claimsJws.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
