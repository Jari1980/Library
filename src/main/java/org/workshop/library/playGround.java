package org.workshop.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.workshop.library.entity.AppUser;
import org.workshop.library.entity.Details;
import org.workshop.library.repository.AppUserRepository;
import org.workshop.library.repository.DetailsRepository;

import java.time.LocalDate;

@Component
public class playGround implements CommandLineRunner {

    AppUserRepository appUserRepository;
    DetailsRepository detailsRepository;

    @Autowired
    public playGround(AppUserRepository appUserRepository, DetailsRepository detailsRepository) {
        this.appUserRepository = appUserRepository;
        this.detailsRepository = detailsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        detailsRepository.save(new Details("Jon", "BarBar", LocalDate.of(2025,01,16)));
        appUserRepository.save(new AppUser("Broccoli", "1234", LocalDate.of(2000,1,1),detailsRepository.findById(1).get()));
        System.out.println("Finding user called Broccoli");
        System.out.println(appUserRepository.findByUsernameIs("Broccoli").getUsername());
        System.out.println("------------------------");
        System.out.println("Find users with reg date between 2000-01-01 and 2025-01-20:");
        System.out.println(appUserRepository.findAppUsersByRegDateBetween(LocalDate.of(2000,1,1),LocalDate.of(2025,01,20)));

        System.out.println("------------------");
        System.out.println("Finding detail ignoring case");

    }
}
