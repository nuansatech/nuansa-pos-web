package id.nuansa.pos.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Component
public class TokenGenerator {

    @Value("${security.secret}")
    private String secretKey;

    @Value("${security.expiration}")
    private Long expiration;

    public String generateToken(String subject, TokenPayload claims) {
        Date issuedAt = new Date();
        Date expiredAt = new Date(issuedAt.getTime() + expiration);

        return Jwts.builder()
                .setSubject(subject)
                .setClaims(claims.toMap())
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generatePassword(String secureId, String plain) throws InvalidKeyException, NoSuchAlgorithmException {
        String concatPlain = secureId + "." + plain;
        HmacAlgorithm hmacAlgorithm = new HmacAlgorithm();
        return hmacAlgorithm.generate(concatPlain, secretKey, hmacAlgorithm.HMAC_SHA256_SIGNATURE);
    }
}
