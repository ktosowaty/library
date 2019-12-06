package pl.edu.wat.wcy.postgres.model;

import javax.persistence.*;

@Entity(name = "books")
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "ISBN", nullable = false, updatable = false, unique = true)
    private String ISBN;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "release_year", nullable = false)
    private int releaseYear;

    protected Book() {
        // empty
    }

    public Book(String ISBN, String name, String publisher, int releaseYear) {
        this.ISBN = ISBN;
        this.name = name;
        this.publisher = publisher;
        this.releaseYear = releaseYear;
    }

    public long getId() {
        return id;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

}
