package org.workshop.library.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.workshop.library.entity.BookLoan;

import java.time.LocalDate;
import java.util.List;

//@Transactional
@Repository
public interface BookLoanRepository extends CrudRepository<BookLoan, Integer> {
    List<BookLoan> findByBorrowerId(int borrowerId);
    List<BookLoan> findByBookId(int bookId);
    List<BookLoan> findByReturnedFalse();
    List<BookLoan> findByReturnedFalseAndDueDateIsBefore(LocalDate dueDate);
    List<BookLoan> findByLoanDateBetween(LocalDate startDate, LocalDate endDate);
    @Modifying
    @Transactional
    @Query("UPDATE BookLoan b SET b.returned = TRUE WHERE b.id = :id")
    //@NativeQuery("UPDATE book_loan SET returned = TRUE WHERE id = :id")
    void updateReturnedTrue(int id);
}
