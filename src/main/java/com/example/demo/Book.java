package com.example.demo;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity(name = "book")
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    @Column(name = "book_name",
    nullable = false)
    private String bookName;
    @Column(name = "create_at",
    columnDefinition ="DATETIME")
    private LocalDateTime createAt;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "book_student_id_fk"))
    private Student student;



    public Book( String bookName, LocalDateTime createAt, Student student) {
        this.bookName = bookName;
        this.createAt = createAt;
        this.student = student;
    }

    public Book(String bookName, LocalDateTime createAt) {
        this.bookName = bookName;
        this.createAt = createAt;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
