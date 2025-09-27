package com.schoolflow.School_flow_api.dto.assesment;

import com.schoolflow.School_flow_api.entities.enums.AssesmentsTypes;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AssesmentDTO {

    private Long id;

    private String title;

    private String description;

    private AssesmentsTypes type;

    private double percentage;

    private Integer period;

    private Integer schoolYear;
}
