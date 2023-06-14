package com.bcipriano.codeGenerator.api.controller;

import com.bcipriano.codeGenerator.api.dto.StudentDTO;
import com.bcipriano.codeGenerator.model.entity.Student;
import com.bcipriano.codeGenerator.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/students", produces = {"application/json"})
@RequiredArgsConstructor
public class StudentController {

    public final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> post(@RequestBody @Valid StudentDTO studentDTO) {
        Student student = converter(studentDTO);
        student = studentService.saveStudent(student);
        return new ResponseEntity<>(StudentDTO.create(student), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> get(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentsPage = studentService.getAll(pageable);
        return ResponseEntity.ok(studentsPage.stream().map(StudentDTO::create).collect(Collectors.toList()));
    }

    public static Student converter(StudentDTO studentDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Student student = modelMapper.map(studentDTO, Student.class);
        return student;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                }
        );
        return errors;
    }

}
