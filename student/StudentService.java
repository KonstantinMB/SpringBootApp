package com.konstantinmb.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll(); //returns a list to us
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email already taken.");
        }
        //if there is no ERROR thrown, the student will be saved to the database:
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with ID " + studentId + " does not exist!");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email){

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with Id " + studentId + " does not exist."));

        if(name != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(),name))
        {
            Optional<Student> studentOptimal = studentRepository.findStudentByEmail(email);
            if(studentOptimal.isPresent()){
                throw new IllegalStateException("Email is already taken my g");
            }
            student.setEmail(email);
        }
    }
}
