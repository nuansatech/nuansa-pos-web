package id.nuansa.pos.api.controller.company;

import id.nuansa.pos.api.model.request.AddCompanyRequest;
import id.nuansa.pos.entity.Company;

public interface CompanyService {

    Company checkExistanceCompany(String name, String email, String typeId);

    Company insert(AddCompanyRequest request);

    Company getOne(String secureId);
}
