package org.myfun.tms_server.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;


@Data
@Component
@ConfigurationProperties(prefix = "myfun.jwt")
public class JwtUtils {

    private Long expire;
    private String secret;

    private String header;

    //生成jwt
    public String generateToken(String username){

        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * expire);

        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

//    解析jwt
    public Claims getClaimsByToken(String jwt){
        try {
           return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e){
            return null;
        }
    }

//    jwt是否过期
    public Boolean isTokenExpired(Claims claims){
        return claims.getExpiration().before(new Date());
    }

}
