package pl.edu.wat.wcy.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.mongo.model.Author;
import pl.edu.wat.wcy.mongo.model.Book;
import pl.edu.wat.wcy.mongo.model.Borrow;
import pl.edu.wat.wcy.mongo.model.Reader;
import pl.edu.wat.wcy.mongo.repository.MongoBorrowRepository;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MongoDataGenerator {

    private static final int READERS_LIMIT = 10000;

    private final MongoBorrowRepository mongoBorrowRepository;

    @Autowired
    public MongoDataGenerator(MongoBorrowRepository mongoBorrowRepository) {
        this.mongoBorrowRepository = mongoBorrowRepository;
    }

    public void generate(int bookLimit, int borrowLimit) {
        List<Reader> readers = generateReaders();
        List<Book> books = generateBooksWithAuthors(bookLimit);
        generateBorrows(readers, books, borrowLimit);
    }

    private List<Reader> generateReaders() {
        Path firstNamesPath = Paths.get("src/main/resources/first_names.txt");
        Path lastNamesPath = Paths.get("src/main/resources/last_names.txt");
        List<String> firstNames = getNames(firstNamesPath);
        List<String> lastNames = getNames(lastNamesPath);
        List<Reader> readers = new ArrayList<>();
        for (int i = 0; i < READERS_LIMIT; i++) {
            Reader reader = new Reader(firstNames.get(i), lastNames.get(READERS_LIMIT - i - 1), generateReaderDate());
            readers.add(reader);
        }
        return readers;
    }

    private List<String> getNames(Path path) {
        try (Stream<String> stream = Files.lines(path)) {
            return stream.limit(READERS_LIMIT)
                    .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private List<Book> generateBooksWithAuthors(int bookLimit) {
        Path path = Paths.get("src/main/resources/BX-Books.csv");
        List<Book> books = new ArrayList<>();
        try (Stream<String> stream = Files.lines(path, Charset.forName("CP1250"))) {
            stream.map(line -> line.replace("\"", ""))
                    .filter(line -> !line.startsWith("ISBN"))
                    .map(line -> line.split(";"))
                    .filter(fields -> fields.length == 8)
                    .limit(bookLimit)
                    .forEach(fields -> {
                        Author author = getAuthor(fields[2], books);
                        Book book = new Book(fields[0], fields[1], fields[4], Integer.parseInt(fields[3]), author);
                        books.add(book);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    private Author getAuthor(String authorName, List<Book> books) {
        for (Book book : books) {
            Author existingAuthor = book.getAuthor();
            if (existingAuthor.getName().equals(authorName)) {
                return existingAuthor;
            }
        }
        return new Author(authorName, generateAuthorDate());
    }

    private void generateBorrows(List<Reader> readers, List<Book> books, int borrowLimit) {
        int borrowsPerReader = borrowLimit / readers.size();
        for (Reader reader : readers) {
            for (int i = 0; i < borrowsPerReader; i++) {
                Book book = books.get(ThreadLocalRandom.current().nextInt(books.size()));
                LocalDate borrowDate = generateBorrowDate();
                LocalDate retrieveDate = generateRetrieveDate(borrowDate);
                int rating = ThreadLocalRandom.current().nextInt(1, 6);
                Borrow borrow = new Borrow(book, reader, borrowDate, retrieveDate, rating);
                mongoBorrowRepository.save(borrow);
            }
        }
    }

    private LocalDate generateBorrowDate() {
        long minDay = LocalDate.of(2005, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 10, 15).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private LocalDate generateRetrieveDate(LocalDate minDate) {
        long minDay = minDate.toEpochDay();
        long maxDay = LocalDate.of(2015, 10, 15).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private LocalDate generateAuthorDate() {
        long minDay = LocalDate.of(1940, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2010, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private LocalDate generateReaderDate() {
        long minDay = LocalDate.of(1980, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2000, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

}
