package id.nuansa.pos.repository;

import id.nuansa.pos.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, String> {

    Discount findBySecureId(String secureId);
}