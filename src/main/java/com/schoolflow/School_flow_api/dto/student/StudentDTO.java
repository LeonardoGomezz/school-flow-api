package com.schoolflow.School_flow_api.dto.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StudentDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 126, message = "la propiedad name minimo debe tener un valor de 2 caracteres y un maximo de 126 caracteres")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 126, message = "la propiedad name minimo debe tener un valor de 2 caracteres y un maximo de 126 caracteres")
    private String lastName;

    private LocalDate birthDate;

    @NotBlank
    @Size(min = 5, max = 126, message = "la propiedad name minimo debe tener un valor de 5 caracteres y un maximo de 126 caracteres")
    private String address;

    @NotBlank
    @Size(min = 5, max = 12, message = "la propiedad name minimo debe tener un valor de 5 caracteres y un maximo de 12 caracteres")
    private String phone;

    @NotBlank
    @Size(min = 2, max = 126, message = "la propiedad name minimo debe tener un valor de 2 caracteres y un maximo de 126 caracteres")
    private String guardianName;

    @NotBlank
    @Size(min = 2, max = 126, message = "la propiedad name minimo debe tener un valor de 2 caracteres y un maximo de 126 caracteres")
    private String guardianPhone;

    @NotBlank
    @Size(min = 2, max = 9, message = "la propiedad name minimo debe tener un valor de 2 caracteres y un maximo de 9 caracteres")
    private String documentType;

    @NotBlank
    @Size(min = 2, max = 20, message = "la propiedad name minimo debe tener un valor de 2 caracteres y un maximo de 20 caracteres")
    private String documentNumber;
}
