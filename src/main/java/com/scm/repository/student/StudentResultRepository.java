package com.scm.repository.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.model.StudentResult;

@Repository
public interface StudentResultRepository extends JpaRepository<StudentResult, Long> {
    @Query("SELECT sr FROM StudentResult sr WHERE sr.student.studentId = :studentId")
    public List<StudentResult> findAllSemesterResult(@Param("studentId") String studentId);

    @Query("SELECT sr FROM StudentResult sr WHERE sr.student.studentId = :studentId AND sr.year = :year AND sr.semester = :semester")
    public StudentResult findByStudentIdAndYearAndSemester(
        @Param("studentId") String studentId,
        @Param("year") int year,
        @Param("semester") int semester
    );
}
