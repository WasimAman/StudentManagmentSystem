package com.scm.controller.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.model.Course;
import com.scm.service.admin.CourseService;

@RestController
@RequestMapping("/api/courses/")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("add")
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        String msg = courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @PostMapping("add/multiple_course")
    public ResponseEntity<String> addMultipleCourses(@RequestBody List<Course> courses) {
        System.out.println("Hello mrrr");
        StringBuilder result = new StringBuilder();
        for (Course course : courses) {
            result.append(courseService.addCourse(course)).append("\n");
        }
        return ResponseEntity.ok(result.toString());
    }
}
