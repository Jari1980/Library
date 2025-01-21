package org.workshop.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.workshop.library.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Book findByIsbnIsIgnoreCase(String title);

    List<Book> findByTitleContaining(String author);

    List<Book> findByMaxLoanDaysLessThan(int maxLoanDays);
}
