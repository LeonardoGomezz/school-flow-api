package com.schoolflow.School_flow_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseDTO {
    private Integer httpStatusCode;
    private String errorType;
    private List<String> message;
}
