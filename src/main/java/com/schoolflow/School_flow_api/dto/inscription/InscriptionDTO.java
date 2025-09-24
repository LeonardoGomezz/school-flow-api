package com.schoolflow.School_flow_api.dto.inscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionDTO {
    private Long id;
    private Long studentId;
    private Long courseid;
    private LocalDate inscriptionDate;
}