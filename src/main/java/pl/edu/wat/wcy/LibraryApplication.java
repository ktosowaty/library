package pl.edu.wat.wcy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.wat.wcy.service.BookAuthorGenerator;
import pl.edu.wat.wcy.service.BorrowGenerator;
import pl.edu.wat.wcy.service.ReaderGenerator;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

    private final BookAuthorGenerator bookAuthorGenerator;
    private final BorrowGenerator borrowGenerator;
    private final ReaderGenerator readerGenerator;

    @Autowired
    public LibraryApplication(BookAuthorGenerator bookAuthorGenerator, BorrowGenerator borrowGenerator,
                              ReaderGenerator readerGenerator) {
        this.bookAuthorGenerator = bookAuthorGenerator;
        this.borrowGenerator = borrowGenerator;
        this.readerGenerator = readerGenerator;
    }

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        int bookLimit = 100000;
//        bookAuthorGenerator.generate(bookLimit);
        readerGenerator.generate();
        borrowGenerator.generate();
    }
}
