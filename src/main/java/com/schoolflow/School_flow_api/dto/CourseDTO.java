package com.schoolflow.School_flow_api.dto;

import jakarta.persistence.Column;
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

    @NotBlank
    @Size(min = 5, max = 100, message = "La propiedad como minimo debe tener un valor de 5 caracteres y maximo de 100 caracteres")
    private String name;

    @NotBlank
    @Size(min = 1)
    private Integer grade;

    @NotBlank
    @Size(min = 4)
    private Integer schoolYear;
}
