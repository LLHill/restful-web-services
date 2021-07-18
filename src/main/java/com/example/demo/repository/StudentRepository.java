package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstNameIn(List<String> firstName);

    @Query("From Student where first_name = :firstName and last_name =:lastName")
    Student getByLastNameAndFirstName (String firstName, String lastName);

    List<Student> findByAddressCity(String city);
}
