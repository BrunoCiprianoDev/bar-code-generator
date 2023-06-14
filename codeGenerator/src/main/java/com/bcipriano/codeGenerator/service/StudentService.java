package com.bcipriano.codeGenerator.service;

import com.bcipriano.codeGenerator.exception.InvalidCpfException;
import com.bcipriano.codeGenerator.exception.InvalidEmailException;
import com.bcipriano.codeGenerator.exception.NotFoundException;
import com.bcipriano.codeGenerator.model.entity.Student;
import com.bcipriano.codeGenerator.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student saveStudent(Student student) {
        if(studentRepository.existsByCpf(student.getCpf())) {
           throw new InvalidCpfException("CPF inválido!");
        }
        if(studentRepository.existsByEmail(student.getEmail())) {
            throw new InvalidEmailException("E-mail inválido!");
        }
        return studentRepository.save(student);
    }

    @Transactional
    public Student getByCpf(String cpf) {
        Optional<Student> studentOptional = studentRepository.findByCpf(cpf);
        if(studentOptional.isEmpty()) {
            throw new NotFoundException("Registro não encontrado!");
        }
        return studentOptional.get();
    }

    @Transactional
    public Page<Student> getAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

}
