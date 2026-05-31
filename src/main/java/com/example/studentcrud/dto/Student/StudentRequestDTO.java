package com.example.studentcrud.dto.Student;

import lombok.Data;

@Data
public class StudentRequestDTO {

    private String name;

    private Long age;

    private String email;

    private String career;

}