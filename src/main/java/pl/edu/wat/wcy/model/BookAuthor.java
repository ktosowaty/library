package pl.edu.wat.wcy.model;

import javax.persistence.*;

@Entity(name = "books_authors")
@Table(name = "books_authors")
public class BookAuthor {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

}
