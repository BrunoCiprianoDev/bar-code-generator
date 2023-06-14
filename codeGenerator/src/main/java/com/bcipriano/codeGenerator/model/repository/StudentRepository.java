package com.bcipriano.codeGenerator.model.repository;

import com.bcipriano.codeGenerator.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    Optional<Student> findByCpf(String cpf);


}
