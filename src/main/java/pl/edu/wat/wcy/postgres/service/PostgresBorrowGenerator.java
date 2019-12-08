package pl.edu.wat.wcy.postgres.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.postgres.model.Book;
import pl.edu.wat.wcy.postgres.model.Borrow;
import pl.edu.wat.wcy.postgres.model.Reader;
import pl.edu.wat.wcy.postgres.repository.BookRepository;
import pl.edu.wat.wcy.postgres.repository.BorrowRepository;
import pl.edu.wat.wcy.postgres.repository.ReaderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PostgresBorrowGenerator {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    @Autowired
    public PostgresBorrowGenerator(BorrowRepository borrowRepository, BookRepository bookRepository,
                                   ReaderRepository readerRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public void generate(int borrowLimit) {
        List<Reader> readers = readerRepository.findAll();
        List<Book> books = bookRepository.findAll();
        int borrowsPerReader = borrowLimit / readers.size();
        for (Reader reader : readers) {
            for (int i = 0; i < borrowsPerReader; i++) {
                Book book = books.get(ThreadLocalRandom.current().nextInt(books.size()));
                LocalDate borrowDate = generateDate();
                LocalDate retrieveDate = generateDate(borrowDate);
                int rating = ThreadLocalRandom.current().nextInt(1, 6);
                Borrow borrow = new Borrow(reader, book, borrowDate, retrieveDate, rating);
                borrowRepository.save(borrow);
            }
        }
    }

    private LocalDate generateDate() {
        long minDay = LocalDate.of(2005, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 10, 15).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private LocalDate generateDate(LocalDate minDate) {
        long minDay = minDate.toEpochDay();
        long maxDay = LocalDate.of(2015, 10, 15).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

}
