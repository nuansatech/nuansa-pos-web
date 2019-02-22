package id.nuansa.pos.api.controller.company;

import id.nuansa.pos.api.model.request.AddCompanyRequest;
import id.nuansa.pos.entity.Company;
import id.nuansa.pos.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company checkExistanceCompany(String name, String email, String typeId) {
        return companyRepository.findByNameAndEmailAndTypeId(name, email, typeId);
    }

    @Override
    public Company insert(AddCompanyRequest request) {
        Company company = new Company();
        company.setSecureId(UUID.randomUUID().toString());
        company.setName(request.getName());
        company.setEmail(request.getEmail());
        company.setAddress(request.getAddress());
        company.setPhone(request.getPhone());
        company.setTypeId(request.getTypeId());
        company.setCreatedAt(new Date());
        company.setUpdatedAt(new Date());

        return companyRepository.save(company);
    }

    @Override
    public Company getOne(String secureId) {
        return companyRepository.findBySecureId(secureId);
    }
}
