package com.konstantinmb.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//this interface is responsible for data access, between the Data Browser and the Service Layer
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    the ID's var is LONG hence why we use long in the key,value pair of the implemented interfaces
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
