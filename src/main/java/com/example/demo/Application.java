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

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

@Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            PageRequest pageRequest=PageRequest.of(0,20,Sort.by("firstName").ascending());
            Page<Student> page = studentRepository.findAll(pageRequest);
            System.out.println(page);


        };

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
