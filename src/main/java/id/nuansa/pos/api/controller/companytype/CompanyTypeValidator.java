package id.nuansa.pos.api.controller.companytype;

import id.nuansa.pos.api.model.request.AddCompanyTypeRequest;
import org.springframework.stereotype.Component;

@Component
public class CompanyTypeValidator {

    public String validateForm(AddCompanyTypeRequest request) {
        if (null == request.getName() || request.getName().isEmpty()) return "name";
        return "";
    }
}
