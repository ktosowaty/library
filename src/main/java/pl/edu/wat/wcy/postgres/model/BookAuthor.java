package pl.edu.wat.wcy.postgres.model;

import javax.persistence.*;

@Entity(name = "books_authors")
@Table(name = "books_authors")
public class BookAuthor {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    protected BookAuthor() {
        // empty
    }

    public BookAuthor(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Author getAuthor() {
        return author;
    }

}
