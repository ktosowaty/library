package pl.edu.wat.wcy.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.mongo.model.Author;

import java.util.List;
import java.util.Optional;

@Repository
public interface MongoAuthorRepository extends MongoRepository<Author, String> {

    Optional<Author> findByName(String name);

    List<Author> findAllByName(String name);

}
