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
public class TeacherDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 100, message = "La propiedad debe tener un valor de minimo  3 caracteres y maximo de 100")
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 100, message = "La propiedad debe tener un valor de minimo  3 caracteres y maximo de 100")
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 200, message = "La propiedad debe tener un valor de minimo  3 caracteres y maximo de 200")
    private String academicBackground;

    @NotBlank
    @Size(min = 3, max = 200, message = "La propiedad debe tener un valor de minimo  3 caracteres y maximo de 200")
    private String address;

    @NotBlank
    @Size(min = 13, max = 13, message = "La propiedad debe tener un valor de minimo  13 caracteres y maximo de 13")
    private String phone;
}
