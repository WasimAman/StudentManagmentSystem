package com.scm.StudentIdGenerator.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.StudentIdGenerator.BranchSequence;

public interface BranchSequenceRepository extends JpaRepository<BranchSequence, String> {}

