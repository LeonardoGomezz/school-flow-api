package com.schoolflow.School_flow_api.dto.grade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GradeDTO {
    private Long id;
    private Long studentId;
    private Long assesmentId;
    private Double score;
}
