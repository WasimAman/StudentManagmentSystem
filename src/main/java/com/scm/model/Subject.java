package com.scm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    private String code;
    private String name;

    private boolean isLab; // true if Lab, false if Regular
}

