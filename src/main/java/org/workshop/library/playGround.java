package org.workshop.library;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.workshop.library.entity.*;
import org.workshop.library.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class playGround implements CommandLineRunner {

    AppUserRepository appUserRepository;
    DetailsRepository detailsRepository;
    BookRepository bookRepository;
    BookLoanRepository bookLoanRepository;
    AuthorRepository authorRepository;

    @Autowired
    public playGround(AppUserRepository appUserRepository, DetailsRepository detailsRepository,
                      BookRepository bookRepository, BookLoanRepository bookLoanRepository,
                      AuthorRepository authorRepository) {
        this.appUserRepository = appUserRepository;
        this.detailsRepository = detailsRepository;
        this.bookRepository = bookRepository;
        this.bookLoanRepository = bookLoanRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional //Needed if using LAZY fetching, not needed if EAGER
    @Override
    public void run(String... args) throws Exception {
        detailsRepository.save(new Details("Jon", "BarBar", LocalDate.of(2025,01,16)));
        appUserRepository.save(new AppUser("Broccoli", "1234", LocalDate.of(2000,1,1),detailsRepository.findById(1).get()));
        /*System.out.println("Finding user called Broccoli");
        System.out.println(appUserRepository.findByUsernameIs("Broccoli").getUsername());
        System.out.println("------------------------");
        System.out.println("Find users with reg date between 2000-01-01 and 2025-01-20:");
        System.out.println(appUserRepository.findAppUsersByRegDateBetween(LocalDate.of(2000,1,1),LocalDate.of(2025,01,20)));

        System.out.println("------------------");
        System.out.println("Finding detail ignoring case");
         */
        bookRepository.save(new Book("1111", "Broccoli", 12));
        List<Book> books = bookRepository.findByMaxLoanDaysLessThan(15);
        for (Book book : books) {
            System.out.println(book.getTitle());
        }

        bookLoanRepository.save(new BookLoan(LocalDate.of(2025,01,10), LocalDate.of(2025,02,01), false, appUserRepository.findById(1).get(), bookRepository.findById(1).get()));
        List<BookLoan> test = bookLoanRepository.findByBorrowerId(1);
        for (BookLoan bookLoan : test) {
            System.out.println(bookLoan.getBook().getTitle());
        }
        List<BookLoan> test2 = bookLoanRepository.findByBookId(1);
        for(BookLoan bookLoan : test2) {
            System.out.println(bookLoan.getReturned());
            bookLoanRepository.updateReturnedTrue(bookLoan.getId());
            System.out.println(bookLoan.getReturned());
        }
        System.out.println(bookLoanRepository.findById(1).get().getReturned());

        authorRepository.save(new Author("John", "Doe"));
        authorRepository.deleteAuthorById(1);

        bookRepository.save(new Book("2222", "Broccoli2", 12));
        bookRepository.save(new Book("3333", "Broccoli3", 12));
        bookRepository.save(new Book("4444", "Broccoli4", 12));

        authorRepository.save(new Author("John2", "Doe2"));
        authorRepository.save(new Author("John3", "Doe3"));
        authorRepository.save(new Author("John4", "Doe4"));

         //Works with EAGER
        Book book = bookRepository.findById(2).orElseThrow();
        book.addAuthor(authorRepository.findById(2).orElseThrow());

        Book book2 = bookRepository.findById(3).orElseThrow();
        book2.addAuthor(authorRepository.findById(3).orElseThrow());

        for(Author author : authorRepository.findAll()) {
            System.out.println(author.getFirstName());
        }


        //Book book = bookRepository.findById(2).orElseThrow();
        //book.addAuthor(authorRepository.findById(2).orElseThrow());

    }
}
