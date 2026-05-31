package com.example.studentcrud.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false)
    private Long age;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,length = 100)
    private String career;
}