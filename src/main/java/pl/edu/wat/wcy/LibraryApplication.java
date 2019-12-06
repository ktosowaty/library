package pl.edu.wat.wcy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.wat.wcy.mongo.service.MongoAuthorGenerator;
import pl.edu.wat.wcy.mongo.service.MongoBookGenerator;
import pl.edu.wat.wcy.mongo.service.MongoBorrowGenerator;
import pl.edu.wat.wcy.mongo.service.MongoReaderGenerator;
import pl.edu.wat.wcy.postgres.service.PostgresBookAuthorGenerator;
import pl.edu.wat.wcy.postgres.service.PostgresBorrowGenerator;
import pl.edu.wat.wcy.postgres.service.PostgresReaderGenerator;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

    private final PostgresBookAuthorGenerator postgresBookAuthorGenerator;
    private final PostgresBorrowGenerator postgresBorrowGenerator;
    private final PostgresReaderGenerator postgresReaderGenerator;
    private final MongoAuthorGenerator mongoAuthorGenerator;
    private final MongoReaderGenerator mongoReaderGenerator;
    private final MongoBookGenerator mongoBookGenerator;
    private final MongoBorrowGenerator mongoBorrowGenerator;

    @Autowired
    public LibraryApplication(PostgresBookAuthorGenerator postgresBookAuthorGenerator,
                              PostgresBorrowGenerator postgresBorrowGenerator,
                              PostgresReaderGenerator postgresReaderGenerator,
                              MongoAuthorGenerator mongoAuthorGenerator,
                              MongoReaderGenerator mongoReaderGenerator,
                              MongoBookGenerator mongoBookGenerator,
                              MongoBorrowGenerator mongoBorrowGenerator) {
        this.postgresBookAuthorGenerator = postgresBookAuthorGenerator;
        this.postgresBorrowGenerator = postgresBorrowGenerator;
        this.postgresReaderGenerator = postgresReaderGenerator;
        this.mongoAuthorGenerator = mongoAuthorGenerator;
        this.mongoReaderGenerator = mongoReaderGenerator;
        this.mongoBookGenerator = mongoBookGenerator;
        this.mongoBorrowGenerator = mongoBorrowGenerator;
    }

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Override
    public void run(String... args) {
        int bookLimit = 20000;
        int borrowLimit = 100000;
//        postgresBookAuthorGenerator.generate(bookLimit);
//        postgresReaderGenerator.generate();
//        postgresBorrowGenerator.generate(borrowLimit);
        mongoAuthorGenerator.generate(bookLimit);
        mongoBookGenerator.generate(bookLimit);
        mongoReaderGenerator.generate();
        mongoBorrowGenerator.generate(borrowLimit);
    }
}
