package pl.edu.wat.wcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.model.BookAuthor;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {
}
