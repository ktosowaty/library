package pl.edu.wat.wcy.postgres.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "borrows")
@Table(name = "borrows")
public class Borrow {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "retrieve_date", nullable = false)
    private LocalDate retrieveDate;

    @Column(name = "rating", nullable = false)
    private int rating;

    protected Borrow() {
        // empty
    }

    public Borrow(Reader reader, Book book, LocalDate borrowDate, LocalDate retrieveDate, int rating) {
        this.reader = reader;
        this.book = book;
        this.borrowDate = borrowDate;
        this.retrieveDate = retrieveDate;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getRetrieveDate() {
        return retrieveDate;
    }

    public int getRating() {
        return rating;
    }
}
