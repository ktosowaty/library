package pl.edu.wat.wcy.model;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "pages", nullable = false)
    private int pages;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    public Book(String ISBN, String name, int pages, LocalDate releaseDate) {
        this.ISBN = ISBN;
        this.name = name;
        this.pages = pages;
        this.releaseDate = releaseDate;
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

    public int getPages() {
        return pages;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

}
