package com.schoolflow.School_flow_api.entities;

import com.schoolflow.School_flow_api.entities.enums.AssesmentsTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "assessments")
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
    @Enumerated(EnumType.STRING)
    private AssesmentsTypes type;

    @Column(name = "percentage")
    private Double percentage;

    @Column(name = "period")
    private Integer period;

    @Column(name = "school_year")
    private Integer schoolYear;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Assesment(String title, String description, AssesmentsTypes type, Double percentage, Integer period, Integer schoolYear){
        this.title = title;
        this.description = description;
        this.type = type;
        this.percentage = percentage;
        this.period = period;
        this.schoolYear = schoolYear;
    }

}
