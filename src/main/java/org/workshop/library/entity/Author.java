package org.workshop.library.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> writtenBooks = new HashSet<>();

    protected Author() {
    }

    public Author(String firstName, String lastName, Set<Book> writtenBooks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.writtenBooks = writtenBooks;
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setWrittenBooks(Set<Book> writtenBooks) {
        this.writtenBooks = writtenBooks;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<Book> getWrittenBooks() {
        return writtenBooks;
    }
}
