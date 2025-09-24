package com.schoolflow.School_flow_api.dto.course;

import com.schoolflow.School_flow_api.dto.teacher.TeacherDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CourseDTO {

    private Long id;
    private Long teacherId;
    private TeacherDTO teacher;

    @NotBlank
    @Size(min = 5, max = 100, message = "La propiedad como minimo debe tener un valor de 5 caracteres y maximo de 100 caracteres")
    private String name;

    @NotBlank
    @Size(min = 1)
    private Integer grade;

    public CourseDTO(Long id, String name, Integer grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
}
