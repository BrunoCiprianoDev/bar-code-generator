package com.bcipriano.codeGenerator.api.dto;

import com.bcipriano.codeGenerator.model.entity.Student;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(value = "The student name", example = "Maria Torres", required = true)
    @NotBlank(message = "O campo nome não foi preenchido.")
    private String name;

    //@Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.")
    @ApiModelProperty(value = "Student CPF", example = "999.999.999-99", required = true)
    @NotBlank(message = "O campo CPF não foi preenchido.")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.")
    private String cpf;

    @ApiModelProperty(value = "Student e-mail", example = "jose@gmail.com", required = true)
    @Email(message = "E-mail inválido.")
    private String email;


    private boolean isInternal;

    @ApiModelProperty(value = "Student e-mail", example = "jose@gmail.com", required = true)
    private String course;


    public static StudentDTO create(Student student) {
        ModelMapper modelMapper = new ModelMapper();
        StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
        return studentDTO;
    }

}
