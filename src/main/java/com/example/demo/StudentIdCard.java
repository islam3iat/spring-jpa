package com.example.demo;

import jakarta.persistence.*;

@Entity(name = "StudentIdCard")
@Table(name = "student_id_card",
uniqueConstraints = {
        @UniqueConstraint(name = "student_card_number_unique",columnNames = "card_number")
})
public class StudentIdCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
  private   Long id;
    @Column(name = "card_number",
    nullable = false,
    length = 15)
    private String cardNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id",
    referencedColumnName = "id",foreignKey =@ForeignKey(name = "student_id_card_student_id_fk") )
    private Student student;

    public StudentIdCard( String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    public StudentIdCard() {
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}

