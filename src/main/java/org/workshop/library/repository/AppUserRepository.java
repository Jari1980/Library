package org.workshop.library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.workshop.library.entity.AppUser;

import java.time.LocalDate;
import java.util.List;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {


    AppUser findByUsernameIs(String username);
    List<AppUser> findAppUsersByRegDateBetween(LocalDate date1, LocalDate date2);
    @Query("SELECT U FROM AppUser U WHERE U.details.id = :detailsId")
    List<AppUser> findAppUsersByDetails_Id(@Param("detailsId") int detailsId);

    @Query("SELECT U FROM AppUser U WHERE LOWER(U.details.email) = LOWER(:email)")
    AppUser findAppUserByEmailIgnoreCase(@Param("email") String email);

}
