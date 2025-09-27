package com.schoolflow.School_flow_api.entities;

import com.schoolflow.School_flow_api.entities.enums.AssesmentsTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "assesments")
@Getter
@Setter
@NoArgsConstructor
public class Assesment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private AssesmentsTypes type;

    @Column(name = "percentage")
    private double percentage;

    @Column(name = "period")
    private Integer period;

    @Column(name = "school_year")
    private Integer schoolYear;

}
