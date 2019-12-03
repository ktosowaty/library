package pl.edu.wat.wcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
