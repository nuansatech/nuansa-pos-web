package id.nuansa.pos.repository;

import id.nuansa.pos.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    Transaction findBySecureId(String secureId);
}
