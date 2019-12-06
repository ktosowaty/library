package pl.edu.wat.wcy.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.postgres.model.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
}
