package id.nuansa.pos.api.controller.companytype;

import id.nuansa.pos.api.model.request.AddCompanyTypeRequest;
import id.nuansa.pos.entity.CompanyType;

public interface CompanyTypeService {

    CompanyType add(AddCompanyTypeRequest request);

    CompanyType update(String secureId, AddCompanyTypeRequest request);

    CompanyType getOne(String secureId);

    CompanyType getOneByName(String name);
}
