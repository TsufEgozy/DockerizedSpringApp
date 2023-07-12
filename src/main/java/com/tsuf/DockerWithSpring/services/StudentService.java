package com.tsuf.DockerWithSpring.services;

import com.tsuf.DockerWithSpring.beans.Student;
import com.tsuf.DockerWithSpring.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

}
