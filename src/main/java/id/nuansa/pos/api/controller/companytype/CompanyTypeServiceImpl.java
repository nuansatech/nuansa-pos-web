package id.nuansa.pos.api.controller.companytype;

import id.nuansa.pos.api.model.request.AddCompanyTypeRequest;
import id.nuansa.pos.entity.CompanyType;
import id.nuansa.pos.repository.CompanyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CompanyTypeServiceImpl implements CompanyTypeService {

    @Autowired
    private CompanyTypeRepository companyTypeRepository;

    @Override
    public CompanyType add(AddCompanyTypeRequest request) {
        CompanyType role = new CompanyType();
        role.setSecureId(UUID.randomUUID().toString());
        role.setName(request.getName());
        role.setCreatedAt(new Date());
        role.setUpdatedAt(new Date());

        return companyTypeRepository.save(role);
    }

    @Override
    public CompanyType update(String secureId, AddCompanyTypeRequest request) {
        CompanyType role = getOne(secureId);
        if (null == role) return null;

        role.setName(request.getName());
        role.setUpdatedAt(new Date());

        return companyTypeRepository.save(role);
    }

    @Override
    public CompanyType getOne(String secureId) {
        return companyTypeRepository.findBySecureId(secureId);
    }

    @Override
    public CompanyType getOneByName(String name) {
        return companyTypeRepository.findByName(name);
    }
}
