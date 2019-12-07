package pl.edu.wat.wcy.mongo.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;

@Document(collection = "borrows")
public class Borrow {

    @Id
    private String id;

    private Book book;

    private Reader reader;

    private LocalDate borrowDate;

    private LocalDate retrieveDate;

    protected Borrow() {
        // empty
    }

    public Borrow(Book book, Reader reader, LocalDate borrowDate, LocalDate retrieveDate) {
        this.book = book;
        this.reader = reader;
        this.borrowDate = borrowDate;
        this.retrieveDate = retrieveDate;
    }

    public String getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getRetrieveDate() {
        return retrieveDate;
    }

}
