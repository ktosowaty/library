package pl.edu.wat.wcy.mongo.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collation = "books")
public class Book {

    @Id
    private long id;

    private String ISBN;

    private String name;

    private String publisher;

    private int releaseYear;

    private List<Author> authors;

    protected Book() {
        // empty
    }

    public Book(String ISBN, String name, String publisher, int releaseYear, List<Author> authors) {
        this.ISBN = ISBN;
        this.name = name;
        this.publisher = publisher;
        this.releaseYear = releaseYear;
        this.authors = authors;
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

    public List<Author> getAuthors() {
        return authors;
    }

}
