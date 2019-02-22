package id.nuansa.pos.api.controller.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdminValidator {

    @Value("${security.key}")
    private String securityKey;

    public Boolean isValidKey(String headerKey) {
        if (null == headerKey) return false;
        return securityKey.equals(headerKey);
    }
}
