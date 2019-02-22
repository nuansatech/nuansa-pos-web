package id.nuansa.pos.api.controller.admin;

import id.nuansa.pos.api.model.response.InitialResponse;
import id.nuansa.pos.entity.Company;
import id.nuansa.pos.entity.CompanyType;
import id.nuansa.pos.entity.Role;
import id.nuansa.pos.entity.User;
import id.nuansa.pos.repository.*;
import id.nuansa.pos.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyTypeRepository companyTypeRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public Boolean isEmptyTables() {
        return companyRepository.count() == 0
                && companyTypeRepository.count() == 0
                && discountRepository.count() == 0
                && productRepository.count() == 0
                && roleRepository.count() == 0
                && transactionRepository.count() == 0
                && userRepository.count() == 0;
    }

    @Override
    public InitialResponse initData() throws InvalidKeyException, NoSuchAlgorithmException {
        InitialResponse response = new InitialResponse();
        response.setRole(addRole());
        response.setCompanyTypes(addCompanyTypes());
        response.setCompany(addCompany(response.getCompanyTypes().get(0).getSecureId()));
        response.setUser(addAdminUser(response.getRole().getSecureId(), response.getCompany().getSecureId()));

        return response;
    }

    private Role addRole() {
        Role role = new Role();
        role.setSecureId(UUID.randomUUID().toString());
        role.setName("Administrator");
        role.setCreatedAt(new Date());
        role.setUpdatedAt(new Date());

        return roleRepository.save(role);
    }

    private List<CompanyType> addCompanyTypes() {
        List<String> types = new ArrayList<>();
        types.add("Software House");
        types.add("Restaurant");
        types.add("Laundry");

        List<CompanyType> companies = new ArrayList<>();
        for (String type : types) {
            CompanyType role = new CompanyType();
            role.setSecureId(UUID.randomUUID().toString());
            role.setName(type);
            role.setCreatedAt(new Date());
            role.setUpdatedAt(new Date());

            companies.add(companyTypeRepository.save(role));
        }
        return companies;
    }

    private Company addCompany(String typeId) {
        Company company = new Company();
        company.setSecureId(UUID.randomUUID().toString());
        company.setName("Nuansa Teknologi");
        company.setEmail("nuansa.teknologi@gmail.com");
        company.setAddress("Yogyakarta");
        company.setPhone("085729891040");
        company.setTypeId(typeId);
        company.setCreatedAt(new Date());
        company.setUpdatedAt(new Date());

        return companyRepository.save(company);
    }

    private User addAdminUser(String roleId, String companyId) throws NoSuchAlgorithmException, InvalidKeyException {
        String secureid = UUID.randomUUID().toString();
        String encryptedPassword = tokenGenerator.generatePassword(secureid, "admin123");

        User user = new User();
        user.setSecureId(secureid);
        user.setName("Rio Swarawan Putra");
        user.setEmail("swarawan.rio@gmail.com");
        user.setPassword(encryptedPassword);
        user.setPhone("085729891040");
        user.setRoleId(roleId);
        user.setCompanyId(companyId);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        return userRepository.save(user);
    }
}
