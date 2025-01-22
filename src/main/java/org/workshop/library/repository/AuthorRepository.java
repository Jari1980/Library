package org.workshop.library.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.workshop.library.entity.Author;

import java.util.Set;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Set<Author> findByFirstName(String firstName);
    Set<Author> findByLastName(String lastName);

    Set<Author> findByFirstNameOrLastNameContaining(String firstName, String lastName);
    Set<Author> findByWrittenBooksId(Integer writtenBooksId);

    @Transactional
    @Modifying
    @Query("UPDATE Author a SET a.firstName = :first_name, a.lastName = :last_name WHERE a.id = :id")
    void updateAuthorFirstNameAndLastName(int id, String firstName, String lastName);

    @Transactional
    @Modifying
    @Query("DELETE FROM Author a WHERE a.id = :id")
    void deleteAuthorById(Integer id);
}
