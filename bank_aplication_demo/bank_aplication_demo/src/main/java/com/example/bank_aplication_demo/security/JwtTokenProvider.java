package com.example.bank_aplication_demo.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenProvider {

    @Value(("$app.jwtSecret"))
    private String jwtSecret;


    @Value("#{new Integer('${app.jwtExpirationInMs}')}")
    private int jwtExpirationInMs;

    private static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    public String generateToken(Authentication authentication){

        UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder().setSubject(Long.toString(userPrincipal.getId())).
                setIssuedAt(new Date()).setExpiration(expiryDate).
                signWith(SignatureAlgorithm.HS512,jwtSecret).compact();

    }

    public Long getJwtFromId(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public Boolean validateToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("Expired jwt token");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported jwt token");
        } catch (MalformedJwtException e) {
            logger.error("Invalid jwt token");
        } catch (SignatureException e) {
            logger.error("Invalid jwt signature");
        } catch (IllegalArgumentException e) {
            logger.error("Jwt claims string is empty");
        }
        return false;
    }
}
