package id.nuansa.pos.repository;

import id.nuansa.pos.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findBySecureId(String secureId);

    Role findByName(String name);
}
