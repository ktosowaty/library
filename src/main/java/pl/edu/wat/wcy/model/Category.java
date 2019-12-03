package pl.edu.wat.wcy.model;

import javax.persistence.*;

@Entity(name = "categories")
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public Category(String name, Book book) {
        this.name = name;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Book getBook() {
        return book;
    }

}
