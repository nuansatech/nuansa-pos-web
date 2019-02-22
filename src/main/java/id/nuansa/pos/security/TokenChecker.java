package id.nuansa.pos.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.nuansa.pos.api.controller.credentials.CredentialService;
import id.nuansa.pos.config.LocalMessageUtils;
import id.nuansa.pos.entity.User;
import id.nuansa.starter.constant.StatusCode;
import id.nuansa.starter.exception.AppException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenChecker {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private LocalMessageUtils messageUtils;

    @Value("${security.secret}")
    private String secretKey;

    private ObjectMapper mapper = new ObjectMapper();

    public Boolean check(String accessToken) {
        if (!accessToken.startsWith("Bearer "))
            throw new AppException("Header format is not valid", StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        String clearAccessToken = accessToken.replace("Bearer ", "");
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(clearAccessToken);

        String secureId = (String) claims.getBody().get("secureId");
        String email = (String) claims.getBody().get("email");

        if (claims.getBody().getExpiration().getTime() < new Date().getTime())
            throw new AppException(messageUtils.tokenExpired(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        User user = credentialService.checkExistanceUser(email);
        if (null == user)
            throw new AppException(messageUtils.dataNotFound() + "user", StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        return user.getSecureId().equals(secureId);
    }
}
