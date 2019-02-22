package id.nuansa.pos.api.model.response;

public class LoginResponse {

    private String accessToken;
    private String secureId;
    private String name;
    private String email;
    private String phone;
    private BasicData role;
    private BasicData company;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSecureId() {
        return secureId;
    }

    public void setSecureId(String secureId) {
        this.secureId = secureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BasicData getRole() {
        return role;
    }

    public void setRole(BasicData role) {
        this.role = role;
    }

    public BasicData getCompany() {
        return company;
    }

    public void setCompany(BasicData company) {
        this.company = company;
    }
}
