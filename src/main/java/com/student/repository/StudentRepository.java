package com.student.repository;

import com.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    void deleteBystuCity(String city);
    boolean existsBystuCity(String city);

    List<Student>findBystuCity(String city);

    Student findByStuCityAndStuMarks(String city,Double marks);

    List<Student> findByStuCityOrStuMarks(String city,Double marks);

    List<Student> findBystuCityContaining(String city);

    List<Student>findBystuNameStartingWith(String name);  // EndingWith()

    // LessThan
    List<Student>findBystuMarksLessThan(Double marks);

    // GreaterThan
    List<Student>findBystuMarksGreaterThan(Double marks);

    //Between
    List<Student>findBystuMarksBetween(Double marks1,Double marks2);  // used for two LocalDate range

    // Like
    List<Student>findBystuNameLike(String name);   // it is similar like findBystuName(String name);

    // In
    List<Student> findByStuCityIn(Collection<String> cities);


    // Distinct
    List<Student> findDistinctByStuName(String name);

}
