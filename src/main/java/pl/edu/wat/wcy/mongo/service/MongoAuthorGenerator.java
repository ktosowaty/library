package pl.edu.wat.wcy.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.mongo.model.Author;
import pl.edu.wat.wcy.mongo.repository.MongoAuthorRepository;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Service
public class MongoAuthorGenerator {

    private final MongoAuthorRepository mongoAuthorRepository;

    @Autowired
    public MongoAuthorGenerator(MongoAuthorRepository mongoAuthorRepository) {
        this.mongoAuthorRepository = mongoAuthorRepository;
    }

    public void generate(int bookLimit) {
        Path path = Paths.get("src/main/resources/BX-Books.csv");
        try (Stream<String> stream = Files.lines(path, Charset.forName("CP1250"))) {
            stream.map(line -> line.replace("\"", ""))
                    .filter(line -> !line.startsWith("ISBN"))
                    .map(line -> line.split(";"))
                    .filter(fields -> fields.length == 8)
                    .limit(bookLimit)
                    .forEach(fields -> {
                        Author author = getAuthor(fields[2]);
                        mongoAuthorRepository.save(author);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Author getAuthor(String authorName) {
        Optional<Author> existingAuthor = mongoAuthorRepository.findByName(authorName);
        return existingAuthor.orElseGet(() -> new Author(authorName, generateDate()));
    }

    private LocalDate generateDate() {
        long minDay = LocalDate.of(1940, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2000, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

}
