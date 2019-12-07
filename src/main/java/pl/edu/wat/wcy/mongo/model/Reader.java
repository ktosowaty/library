package pl.edu.wat.wcy.mongo.model;

import java.time.LocalDate;

public class Reader {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    protected Reader() {
        // empty
    }

    public Reader(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

}
