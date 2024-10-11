package com.myLearning.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // don't have to remember everything
    // we have to focus on the flow and the concept

    public static final String SECRETS = "357538782F4125442A472D4B6150645367566B59703373367639792442264528";

    public String generateJwtToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createClaims(claims, userName);
    }

    private String createClaims(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRETS);
        //algo to generate the signIn key
        return Keys.hmacShaKeyFor(keyBytes);
    }


//************ verification and token based login**********//

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
        // revision purpose
        // return extractClaim(token, claims -> claims.getSubject());
        // return extractClaim(token, new Function<Claims, String>() {
        //        @Override
        //        public String apply(Claims claims) {
        //            return claims.getSubject();
        //        }
        //    });
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
        // revision purpose
        // return extractClaim(token, claims -> claims.getExpiration());
        // return extractClaim(token, new Function<Claims, Date>() {
        //        @Override
        //        public Date apply(Claims claims) {
        //            return claims.getExpiration();
        //        }
        //    });

    }

    public <R> R extractClaim(String token, Function<Claims, R> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
