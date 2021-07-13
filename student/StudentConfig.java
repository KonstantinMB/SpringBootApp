package com.konstantinmb.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student konstantin = new Student(
                    "Konstantin",
                    "kok1hahabor@gmail.com",
                    LocalDate.of(1999, Month.SEPTEMBER,25)
            );
            Student desi = new Student(
                    "Desi",
                    "desipopova@gmail.com",
                    LocalDate.of(2001, Month.MARCH,30)
            );
            Student christian = new Student(
                    "Christian",
                    "christianmartayan@gmail.com",
                    LocalDate.of(2001, Month.AUGUST,26)
            );
            // in order to save those students into our "student" database:
            repository.saveAll(
                    List.of(konstantin,desi,christian)
            );
        };
    }
}
