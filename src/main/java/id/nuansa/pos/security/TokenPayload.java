package id.nuansa.pos.security;

import java.util.HashMap;
import java.util.Map;

public class TokenPayload {

    private String secureId;
    private String email;
    private String company;
    private String role;

    public String getSecureId() {
        return secureId;
    }

    public void setSecureId(String secureId) {
        this.secureId = secureId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("secureId", secureId);
        map.put("email", email);
        map.put("company", company);
        map.put("role", role);

        return map;
    }
}
