package com.scm.serviceImpl.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.StudentIdGenerator.service.BranchIdService;
import com.scm.config.jwt.JwtProvider;
import com.scm.exceptions.CourseException;
import com.scm.exceptions.StudentException;
import com.scm.model.Course;
import com.scm.model.FeeStructure;
import com.scm.model.Student;
import com.scm.model.enums.BranchWiseAcademicFee;
import com.scm.model.enums.FeeStatus;
import com.scm.model.enums.RefundStatus;
import com.scm.model.enums.Role;
import com.scm.repository.CourseRepository;
import com.scm.repository.student.StudentRepository;
import com.scm.request.LoginRequest;
import com.scm.request.RegisterRequest;
import com.scm.service.Student.StudentAuthService;
import com.scm.serviceImpl.CustomStudentDetailsService;
import com.scm.studentPasswordGenerator.StudentPasswordGenerator;

@Service
public class StudentAuthServiceImple implements StudentAuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BranchIdService branchIdService;

    @Autowired
    private BranchWiseAcademicFee branchWiseAcademicFee;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CustomStudentDetailsService customStudentDetailsService;

    @Autowired
    private StudentPasswordGenerator passwordGenerator;

    @Override
    public String register(RegisterRequest request) {
        Student student = getNewStudent(request);
        studentRepository.save(student);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(student.getRole().name()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(student.getStudentId(), null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }

    private FeeStructure getFeeStructure(String brachCode) {
        FeeStructure feeStructure = new FeeStructure();
        feeStructure.setYear(1);
        feeStructure.setAcademicFee(branchWiseAcademicFee.getAcademicFee(brachCode));
        feeStructure.setExamFee(branchWiseAcademicFee.getExamFee());
        feeStructure.setOtherFee(branchWiseAcademicFee.getOtherFee());
        feeStructure.setPaidAmount(0);
        feeStructure.setRefundAmount(0);
        feeStructure.setRefundStatus(RefundStatus.NOT_REQUESTED);
        feeStructure.setStatus(FeeStatus.UNPAID);

        return feeStructure;
    }

    public Student getNewStudent(RegisterRequest request) {
        Optional<Student> isPersent = studentRepository.findByEmail(request.getEmail());
        if (!isPersent.isEmpty()) {
            throw new StudentException("Email already exist please try with another...");
        }

        String studentPassword = passwordGenerator.generatePassword();

        Student student = new Student();
        student.setStudentId(branchIdService.generateNextId(request.getBranchCode()));
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPassword(passwordEncoder.encode(studentPassword));
        student.setStudentPhone(request.getStudentPhone());
        student.setParentsPhone(request.getParentsPhone());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGender(request.getGender());
        student.setAddress(request.getAddress());
        student.setFatherName(request.getFatherName());
        student.setMotherName(request.getMotherName());
        student.setRole(Role.STUDENT);
        student.setCurrentYear(1);
        student.setCurrentSemester(1);

        Course course = getCourse(request.getBranchCode(), request.getBranchName(), request.getCourseName());

        student.setCourse(course);

        FeeStructure feeStructure = getFeeStructure(request.getBranchCode());
        feeStructure.setStudent(student);

        student.getStudentFeeStructures().add(feeStructure);

        System.out.println("*****************************************");
        System.out.println("Student password:- " +studentPassword);
        System.out.println("*****************************************");
        
        return student;
    }

    private Course getCourse(String branchCode, String branchName, String courseName) {
        Optional<Course> opt = courseRepository.findByNameAndBranchAndBranchCode(courseName, branchName, branchCode);

        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new CourseException("Sorry please try another branch");
        }
    }

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticate(request.getStudentId(), request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return token;
    }

    private Authentication authenticate(String studentId, String password) {
        UserDetails student = customStudentDetailsService.loadUserByUsername(studentId);
        if(!passwordEncoder.matches(password, student.getPassword())){
            throw new BadCredentialsException("Invalid Id or password");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(student, null,student.getAuthorities());
        return authentication;
    }
}
