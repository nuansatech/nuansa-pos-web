package id.nuansa.pos.repository;

import id.nuansa.pos.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    Company findBySecureId(String secureId);

    Company findByNameAndEmailAndTypeId(String name, String email, String typeId);
}