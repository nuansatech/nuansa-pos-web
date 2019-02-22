package id.nuansa.pos.api.controller.company;

import id.nuansa.pos.api.model.request.AddCompanyRequest;
import org.springframework.stereotype.Component;

@Component
public class CompanyValidator {

    public String validateForm(AddCompanyRequest request) {
        if (null == request.getName() || request.getName().isEmpty()) return "name";
        if (null == request.getEmail() || request.getEmail().isEmpty()) return "email";
        if (null == request.getAddress() || request.getAddress().isEmpty()) return "address";
        if (null == request.getPhone()) return "phone";
        if (null == request.getTypeId() || request.getTypeId().isEmpty()) return "company type";
        return "";
    }
}
