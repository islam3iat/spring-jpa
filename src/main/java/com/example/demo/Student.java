package com.example.demo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="Student")
@Table( name = "student",
        uniqueConstraints ={
        @UniqueConstraint(name="student_unique_email",columnNames = "email")
})
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Column(
                name = "id"
        )
    private Long id;
    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(
            name = "age",
            nullable = false
    )
    private Integer age;
    @OneToOne(mappedBy = "student",
    orphanRemoval = true,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private StudentIdCard studentIdCard;

    @OneToMany(mappedBy = "student",
    orphanRemoval = true,
    cascade ={ CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Book> books=new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinTable(name = "enrolment",
            joinColumns = @JoinColumn(
                    name = "student_id",
                    foreignKey = @ForeignKey(name = "enrolment_student_id_fk")),
            inverseJoinColumns = @JoinColumn(
                    name = "course",
                    foreignKey = @ForeignKey(name = "enrolment_course_id_fk")
            )
    )
    private List<Course> courses=new ArrayList<>();


    public Student( String firstName, String lastName, String email, Integer age) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student() {
    }

    public void addBook(Book book){
        if (!this.books.contains(book)) {
           this.books.add(book);
           book.setStudent(this);
        }
    }
    public void removeBook(Book book){
        if (this.books.contains(book)) {
            this.books.remove(book);
            book.setStudent(null);
        }
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public StudentIdCard getStudentIdCard() {
        return studentIdCard;
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }
    public void enrolToCourse(Course course){
        courses.add(course);
        course.getStudents().add(this);
    }
    public void unEnrolCourse(Course course){
        courses.remove(course);
        course.getStudents().remove(this);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
