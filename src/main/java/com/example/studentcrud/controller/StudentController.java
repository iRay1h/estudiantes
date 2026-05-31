package com.example.studentcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.studentcrud.dto.Student.StudentRequestDTO;
import com.example.studentcrud.service.StudentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public String listStudents(Model model){

        model.addAttribute("students",
                studentService.listStudents());

        return "index";
    }

    @GetMapping("/new")
    public String createForm(Model model){

        model.addAttribute("student",
                new StudentRequestDTO());

        return "create-student";
    }

    @PostMapping("/save")
    public String saveStudent(
            @ModelAttribute StudentRequestDTO student){

        studentService.createStudent(student);

        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editForm(
            @PathVariable Long id,
            Model model){

        model.addAttribute("student",
                studentService.getStudentById(id));

        return "edit-student";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(
            @PathVariable Long id,
            @ModelAttribute StudentRequestDTO student){

        studentService.updateStudent(id, student);

        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(
            @PathVariable Long id){

        studentService.deleteStudent(id);

        return "redirect:/students";
    }

}