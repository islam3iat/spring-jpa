package com.example.demo;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

@Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {

            Faker faker = new Faker();

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));

            student.addBook(
                    new Book("Clean Code", LocalDateTime.now().minusDays(4)));


            student.addBook(
                    new Book("Think and Grow Rich", LocalDateTime.now()));


            student.addBook(
                   new Book("Spring Data JPA", LocalDateTime.now().minusYears(1)));

            student.enrolToCourse(new Course("ado","mi"));
            student.enrolToCourse(new Course("sm","mi"));


            StudentIdCard studentIdCard = new StudentIdCard(
                    "123456789",
                    student);
            student.setStudentIdCard(studentIdCard);
            studentRepository.save(student);



        };

    }

    private static void addStudentIdCard(StudentIdCardRepository studentIdCardRepository) {
        Faker faker =new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email=String.format("%s.%s@start.edu",firstName,lastName);
        Student student = new Student(firstName, lastName, email, faker.number().numberBetween(17, 55));
        StudentIdCard studentIdCard=new StudentIdCard("123456789",student);

        studentIdCardRepository.save(studentIdCard);
    }

    private static void pages(StudentRepository studentRepository) {
        PageRequest pageRequest=PageRequest.of(0,20,Sort.by("firstName").ascending());
        Page<Student> page = studentRepository.findAll(pageRequest);
        System.out.println(page);
    }

    private static void sorting(StudentRepository studentRepository) {
        Sort sort = Sort.by("firstName").ascending().and(Sort.by("age").ascending());
        studentRepository.findAll(sort).forEach(System.out::println);
    }

    private static void generateRandomStudents(StudentRepository studentRepository) {
        Faker faker =new Faker();
        for (int i=0;i<20;i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email=String.format("%s.%s@start.edu",firstName,lastName);
            Student student = new Student(firstName, lastName, email, faker.number().numberBetween(17, 55));
            studentRepository.save(student);
        }
    }
}
