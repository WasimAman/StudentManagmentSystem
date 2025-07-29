package com.scm.repository.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.model.FeeStructure;

@Repository
public interface StudentFeeStructureRepository extends JpaRepository<FeeStructure,Long>{
    @Query("SELECT fs FROM FeeStructure fs WHERE fs.student.studentId = :studentId")
    public List<FeeStructure> findAllFeeStructureForStudent(@Param("studentId") String studentId);

    @Query("SELECT fs FROM FeeStructure fs WHERE fs.student.studentId = :studentId AND fs.year = :year")
    public FeeStructure findByStudentIdAndYear(
        @Param("studentId") String studentId,
        @Param("year") int year
    );
}
