package org.workshop.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.workshop.library.entity.AppUser;
import org.workshop.library.entity.Book;
import org.workshop.library.entity.BookLoan;
import org.workshop.library.entity.Details;
import org.workshop.library.repository.AppUserRepository;
import org.workshop.library.repository.BookLoanRepository;
import org.workshop.library.repository.BookRepository;
import org.workshop.library.repository.DetailsRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class playGround implements CommandLineRunner {

    AppUserRepository appUserRepository;
    DetailsRepository detailsRepository;
    BookRepository bookRepository;
    BookLoanRepository bookLoanRepository;

    @Autowired
    public playGround(AppUserRepository appUserRepository, DetailsRepository detailsRepository,
                      BookRepository bookRepository, BookLoanRepository bookLoanRepository) {
        this.appUserRepository = appUserRepository;
        this.detailsRepository = detailsRepository;
        this.bookRepository = bookRepository;
        this.bookLoanRepository = bookLoanRepository;
    }

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

    }
}
