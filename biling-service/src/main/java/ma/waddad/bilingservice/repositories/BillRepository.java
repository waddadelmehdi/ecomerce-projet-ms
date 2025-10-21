package ma.waddad.bilingservice.repositories;

import ma.waddad.bilingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
