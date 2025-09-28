package com.schoolflow.School_flow_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "grades")
@Getter
@Setter
@NoArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "score")
    private Double score;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "assessment_id", nullable = false)
    private Assesment assesment;

    public Grade(Long studentId, Long assesmentId, Double score){
        Student s = new Student();
        s.setId(studentId);
        this.student = s;

        Assesment a = new Assesment();
        a.setId(assesmentId);
        this.assesment = a;
        this.score = score;
    }
}
