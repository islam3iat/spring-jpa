package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("SELECT s from Student s WHERE s.firstName=?1 AND s.age=?2 ")
    List<Student> findStudentsByFirstNameEqualsAndAgeEquals(String firstName,Integer age);
    @Query(value = "SELECT * FROM student WHERE firistName=?1 AND age=?2" ,nativeQuery = true)
    List<Student> findStudentsByFirstNameEqualsAndAgeEqualsNative(String firstName,Integer age);
    @Query(value = "SELECT * FROM student WHERE :firstName AND :age" ,nativeQuery = true)
    List<Student> findStudentsByFirstNameEqualsAndAgeEqualsNative2(@Param("firstName") String firstName,
                                                                  @Param("age") Integer age);
    @Transactional
    @Modifying
    @Query("DELETE FROM Student s WHERE s.id=?1")
    void deleteStudentById(Long id);
}
