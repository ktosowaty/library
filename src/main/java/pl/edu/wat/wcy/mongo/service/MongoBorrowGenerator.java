package pl.edu.wat.wcy.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.mongo.model.Book;
import pl.edu.wat.wcy.mongo.model.Borrow;
import pl.edu.wat.wcy.mongo.model.Reader;
import pl.edu.wat.wcy.mongo.repository.MongoBookRepository;
import pl.edu.wat.wcy.mongo.repository.MongoBorrowRepository;
import pl.edu.wat.wcy.mongo.repository.MongoReaderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MongoBorrowGenerator {

    private final MongoBorrowRepository mongoBorrowRepository;
    private final MongoBookRepository mongoBookRepository;
    private final MongoReaderRepository mongoReaderRepository;

    @Autowired
    public MongoBorrowGenerator(MongoBorrowRepository mongoBorrowRepository, MongoBookRepository mongoBookRepository,
                                MongoReaderRepository mongoReaderRepository) {
        this.mongoBorrowRepository = mongoBorrowRepository;
        this.mongoBookRepository = mongoBookRepository;
        this.mongoReaderRepository = mongoReaderRepository;
    }

    public void generate(int borrowLimit) {
        List<Reader> readers = mongoReaderRepository.findAll();
        List<Book> books = mongoBookRepository.findAll();
        int borrowsPerReader = borrowLimit / readers.size();
        for (Reader reader : readers) {
            for (int i = 0; i < borrowsPerReader; i++) {
                Book book = books.get(ThreadLocalRandom.current().nextInt(books.size()));
                LocalDate borrowDate = generateDate();
                LocalDate retrieveDate = generateDate(borrowDate);
                Borrow borrow = new Borrow(book, reader, borrowDate, retrieveDate);
                mongoBorrowRepository.save(borrow);
            }
        }
    }

    private LocalDate generateDate() {
        long minDay = LocalDate.of(2000, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2019, 10, 15).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private LocalDate generateDate(LocalDate minDate) {
        long minDay = minDate.toEpochDay();
        long maxDay = LocalDate.of(2019, 10, 15).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

}
