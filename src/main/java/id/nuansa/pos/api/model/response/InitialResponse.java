package id.nuansa.pos.api.model.response;

import id.nuansa.pos.entity.Company;
import id.nuansa.pos.entity.CompanyType;
import id.nuansa.pos.entity.Role;
import id.nuansa.pos.entity.User;

import java.util.List;

public class InitialResponse {

    private Role role;
    private List<CompanyType> companyTypes;
    private Company company;
    private User user;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<CompanyType> getCompanyTypes() {
        return companyTypes;
    }

    public void setCompanyTypes(List<CompanyType> companyTypes) {
        this.companyTypes = companyTypes;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
