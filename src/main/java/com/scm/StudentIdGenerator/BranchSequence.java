package com.scm.StudentIdGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branch_sequences")
public class BranchSequence {

    @Id
    private String branchName;
    private int sequence;
}
