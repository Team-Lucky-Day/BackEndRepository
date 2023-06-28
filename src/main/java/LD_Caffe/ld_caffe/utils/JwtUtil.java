package LD_Caffe.ld_caffe.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil  {

    //token 에서 userId get 메서드
    public static String getUserId(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
                .get("userId",String.class);
    }

    //jwt 만료 검증 메서드
    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration()
                .before(new Date(System.currentTimeMillis()));
    }

    //jwt 만드는 메서드
    public static String createJwt(String userId,String secretKey,Long expireMs){
        Claims claims = Jwts.claims();
        claims.put("userId",userId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expireMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
