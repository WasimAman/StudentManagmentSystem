package com.scm.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scm.model.enums.Role;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    private String studentId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String studentPhone;
    private String parentsPhone;
    private LocalDate dateOfBirth;
    private String gender;

    @Embedded
    private Address address;
    
    private String fatherName;
    private String motherName;
    private int currentYear;
    private int currentSemester;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentResult> results = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<FeeStructure> studentFeeStructures = new ArrayList<>();
}
