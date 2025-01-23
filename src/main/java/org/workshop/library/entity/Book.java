package org.workshop.library.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String isbn;
    private String title;
    private int maxLoanDays;
    @ManyToMany(mappedBy = "writtenBooks", fetch = FetchType.EAGER)
    private Set<Author> authors = new HashSet<>();

    protected Book() {
    }

    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
        author.removeWrittenBook(this);
    }

    public void addAuthor(Author author) {
        authors.add(author);
        author.addWrittenBook(this);
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }
}
