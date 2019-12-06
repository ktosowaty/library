package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.model.Book;
import pl.edu.wat.wcy.model.Borrow;
import pl.edu.wat.wcy.model.Reader;
import pl.edu.wat.wcy.repository.BookRepository;
import pl.edu.wat.wcy.repository.BorrowRepository;
import pl.edu.wat.wcy.repository.ReaderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BorrowGenerator {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    @Autowired
    public BorrowGenerator(BorrowRepository borrowRepository, BookRepository bookRepository,
                           ReaderRepository readerRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public void generate() {
        List<Reader> readers = readerRepository.findAll();
        List<Book> books = bookRepository.findAll();
        for (Reader reader : readers) {
            for (int i = 0; i < 3; i++) {
                Book book = books.get(ThreadLocalRandom.current().nextInt(books.size()));
                LocalDate borrowDate = generateDate();
                LocalDate retrieveDate = generateDate(borrowDate);
                Borrow borrow = new Borrow(reader, book, borrowDate, retrieveDate);
                borrowRepository.save(borrow);
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
