package id.nuansa.pos.repository;

import id.nuansa.pos.entity.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTypeRepository extends JpaRepository<CompanyType, String> {

    CompanyType findBySecureId(String secureId);

    CompanyType findByName(String name);
}