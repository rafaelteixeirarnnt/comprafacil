package br.com.leaf.usuarios.services;

import br.com.leaf.usuarios.enums.PerfisEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtService {

    private static final String SECRET = "79063e8037fff16d297a1fe65136f1251126cddb2cc9870ecf8d653835538e85";

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }


    public String generateToken(String email, PerfisEnum perfil, Long id) {
        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", perfil);
//
//        if (perfil == PerfisEnum.CLIENTE) {
//            claims.put("cartId", UUID.randomUUID().toString());
//            claims.put("userId", id);
//        }

        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
