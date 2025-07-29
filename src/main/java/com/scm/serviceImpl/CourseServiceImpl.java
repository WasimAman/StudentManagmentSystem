package com.scm.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.exceptions.CourseException;
import com.scm.model.Course;
import com.scm.repository.CourseRepository;
import com.scm.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public String addCourse(Course course) {
        Optional<Course> opt = courseRepository.findByNameAndBranchAndBranchCode(course.getName(), course.getBranch(), course.getBranchCode());

        if(opt.isPresent()){
            throw new CourseException("Course Already persent...");
        }
        courseRepository.save(course);
        return "Course Added successfully";
    }

}
