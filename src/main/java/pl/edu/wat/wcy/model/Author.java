package pl.edu.wat.wcy.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "authors")
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    public Author(String firstName, String lastName, String country, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

}
