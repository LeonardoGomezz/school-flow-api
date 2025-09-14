package com.schoolflow.School_flow_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String fristName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "academic_background")
    private String academicBackground;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    public Teacher(String firstName, String lastName, String academicBackground, String address, String phone){
        this.fristName = firstName;
        this.lastName = lastName;
        this.academicBackground = academicBackground;
        this.address = address;
        this.phone = phone;
    }
}
