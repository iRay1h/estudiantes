package com.example.studentcrud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.studentcrud.dto.Student.StudentMessage;
import com.example.studentcrud.dto.Student.StudentRequestDTO;
import com.example.studentcrud.dto.Student.StudentResponseDTO;
import com.example.studentcrud.entity.StudentEntity;
import com.example.studentcrud.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<StudentResponseDTO> listStudents(){

        List<StudentEntity> studentsFound = studentRepository.findAll();

        List<StudentResponseDTO> response = new ArrayList<>();

        for(StudentEntity student : studentsFound){

            StudentResponseDTO dto = new StudentResponseDTO();

            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setAge(student.getAge());
            dto.setEmail(student.getEmail());
            dto.setCareer(student.getCareer());

            response.add(dto);
        }

        return response;
    }

    public StudentResponseDTO getStudentById(Long id){

        Optional<StudentEntity> studentFound = studentRepository.findById(id);

        if(studentFound.isEmpty()){
            return null;
        }

        StudentEntity student = studentFound.get();

        StudentResponseDTO response = new StudentResponseDTO();

        response.setId(student.getId());
        response.setName(student.getName());
        response.setAge(student.getAge());
        response.setEmail(student.getEmail());
        response.setCareer(student.getCareer());

        return response;
    }

    public StudentMessage createStudent(StudentRequestDTO request){

        StudentMessage response = new StudentMessage();

        if(studentRepository.existsByEmail(request.getEmail())){

            response.setMessage("El correo ya existe");
            return response;
        }

        StudentEntity student = new StudentEntity();

        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setCareer(request.getCareer());

        studentRepository.save(student);

        response.setMessage("Estudiante creado correctamente");

        return response;
    }

    public StudentMessage updateStudent(Long id, StudentRequestDTO request){

        StudentMessage response = new StudentMessage();

        Optional<StudentEntity> studentFound = studentRepository.findById(id);

        if(studentFound.isEmpty()){

            response.setMessage("Estudiante no encontrado");
            return response;
        }

        StudentEntity student = studentFound.get();

        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setCareer(request.getCareer());

        studentRepository.save(student);

        response.setMessage("Estudiante actualizado correctamente");

        return response;
    }

    public StudentMessage deleteStudent(Long id){

        StudentMessage response = new StudentMessage();

        if(!studentRepository.existsById(id)){

            response.setMessage("Estudiante no encontrado");
            return response;
        }

        studentRepository.deleteById(id);

        response.setMessage("Estudiante eliminado correctamente");

        return response;
    }

}