package pl.edu.wat.wcy.postgres.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "authors")
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    protected Author() {
        // empty
    }

    public Author(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

}
