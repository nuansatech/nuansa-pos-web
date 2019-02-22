package id.nuansa.pos.repository;

import id.nuansa.pos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findBySecureId(String secureId);

    User findByEmail(String email);
}
