package pl.edu.wat.wcy.mongo.model;

public class Book {

    private String ISBN;

    private String name;

    private String publisher;

    private int releaseYear;

    private Author author;

    protected Book() {
        // empty
    }

    public Book(String ISBN, String name, String publisher, int releaseYear, Author author) {
        this.ISBN = ISBN;
        this.name = name;
        this.publisher = publisher;
        this.releaseYear = releaseYear;
        this.author = author;
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

    public Author getAuthor() {
        return author;
    }
}
