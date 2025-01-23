package org.workshop.library.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private LocalDate regDate;
    @OneToOne
    @JoinColumn
    private Details details;
    @OneToMany(mappedBy = "borrower")
    //@JoinColumn
    private List<BookLoan> bookLoanList = new ArrayList<BookLoan>();

    protected AppUser() {
    }

    public AppUser(String username, String password, LocalDate regDate, Details details) {
        this.username = username;
        this.password = password;
        this.regDate = regDate;
        this.details = details;
    }

    public void loanBook(BookLoan loan) {
        bookLoanList.add(loan);
        loan.setBorrower(this);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public Details getDetails() {
        return details;
    }
}
