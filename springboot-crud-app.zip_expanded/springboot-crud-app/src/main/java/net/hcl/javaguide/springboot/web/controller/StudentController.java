package net.hcl.javaguide.springboot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.hcl.javaguide.springboot.web.entity.Student;
import net.hcl.javaguide.springboot.web.exception.ResourceNotFoundException;
import net.hcl.javaguide.springboot.web.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // get all students
    @GetMapping
    public List < Student > getAllStudents() {
        return this.studentRepository.findAll();
    }

    // get students by id
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable(value = "id") long studentId) {
        return this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student not found with id :" + studentId));
    }

    // create student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return this.studentRepository.save(student);
    }

    // update student
    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") long studentId) {
    	Student existingStudent = this.studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id :" + studentId));
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        return this.studentRepository.save(existingStudent);
    }

    // delete student by id
    @DeleteMapping("/{id}")
    public ResponseEntity < Student > deleteStudent(@PathVariable("id") long studentId) {
    	Student existingUser = this.studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id :" + studentId));
        this.studentRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}