package id.nuansa.pos.api.model.response;

public class AddCompanyResponse {

    private String secureId;
    private String name;
    private String email;
    private String address;
    private String phone;
    private BasicData type;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BasicData getType() {
        return type;
    }

    public void setType(BasicData type) {
        this.type = type;
    }
}
