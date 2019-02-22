package id.nuansa.pos.api.model.response;

public class BasicData {

    private String secureId;
    private String name;

    public BasicData(String secureId, String name) {
        this.secureId = secureId;
        this.name = name;
    }

    public String getSecureId() {
        return secureId;
    }

    public String getName() {
        return name;
    }
}
