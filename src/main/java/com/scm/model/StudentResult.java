package com.scm.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private int year;
    private int semester;
    private double totalMarks;
    private double obtainedMarks;
    private double percentage;
    private double sgpa;
    private String grade;
    private LocalDate resultDate;

    @OneToMany(mappedBy = "studentResult", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubjectResult> subjectResults;
}
