package pl.edu.wat.wcy.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.mongo.model.Borrow;

@Repository
public interface MongoBorrowRepository extends MongoRepository<Borrow, String> {
}
