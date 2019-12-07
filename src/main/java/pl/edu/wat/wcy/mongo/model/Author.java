package pl.edu.wat.wcy.mongo.model;

import java.time.LocalDate;

public class Author {

    private String name;

    private LocalDate birthDate;

    protected Author() {
        // empty
    }

    public Author(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

}
