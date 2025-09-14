package com.schoolflow.School_flow_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "school_year")
    private Integer schoolYear;

    public Course(String name, Integer grade, Integer schoolYear){
        this.name = name;
        this.grade = grade;
        this.schoolYear = schoolYear;
    }
}
