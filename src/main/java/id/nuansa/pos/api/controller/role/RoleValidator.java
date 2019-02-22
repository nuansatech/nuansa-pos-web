package id.nuansa.pos.api.controller.role;

import id.nuansa.pos.api.model.request.AddRoleRequest;
import org.springframework.stereotype.Component;

@Component
public class RoleValidator {

    public String validateForm(AddRoleRequest request) {
        if (null == request.getName() || request.getName().isEmpty()) return "name";
        return "";
    }
}
