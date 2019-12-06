package pl.edu.wat.wcy.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.postgres.model.Borrow;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
