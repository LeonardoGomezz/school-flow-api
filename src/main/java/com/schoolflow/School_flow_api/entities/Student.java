package com.schoolflow.School_flow_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "guardian_name")
    private String guardianName;

    @Column(name = "guardian_phone")
    private String guardianPhone;

    @ManyToOne
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    @JoinColumn(name = "document_number", nullable = false)
    private String documentNumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscription> inscriptions = new ArrayList<>();

    public Student(String firstName, String lastName, LocalDate birthDate, String addres, String phone, String guardianName, String guardianPhone, DocumentType documentType, String documentNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = addres;
        this.phone = phone;
        this.guardianName = guardianName;
        this.guardianPhone = guardianPhone;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }
}
