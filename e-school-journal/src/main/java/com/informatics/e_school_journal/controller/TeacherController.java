package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.qualification.CreateQualificationDto;
import com.informatics.e_school_journal.dto.subject.SubjectDto;
import com.informatics.e_school_journal.dto.teacher.CreateTeacherDto;
import com.informatics.e_school_journal.dto.teacher.TeacherDto;
import com.informatics.e_school_journal.dto.teacher.TeacherPersonalInfoDto;
import com.informatics.e_school_journal.dto.teacher.UpdateTeacherDto;
import com.informatics.e_school_journal.service.QualificationService;
import com.informatics.e_school_journal.service.StudyingService;
import com.informatics.e_school_journal.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableMethodSecurity(prePostEnabled = true)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    private final QualificationService qualificationService;
    private final StudyingService studyingService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public TeacherDto createTeacher(@RequestBody @Valid CreateTeacherDto createTeacherDto) {
        return this.teacherService.createTeacher(createTeacherDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/add-role/{userId}")
    public ResponseEntity<TeacherDto> createTeacherRole(@PathVariable String userId) {
        try {
            return new ResponseEntity<>(teacherService.createTeacherRole(userId), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public TeacherDto getTeacherById(@PathVariable String id) {
        return this.teacherService.getTeacherById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public List<TeacherDto> getTeachers() {
        return this.teacherService.getTeachers();
    }

    @PreAuthorize("hasAuthority('director')")
    @GetMapping("/director")
    public List<TeacherPersonalInfoDto> getTeachersByDirector() {
        return this.teacherService.getTeachersByDirector();
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public TeacherDto updateTeacher(@PathVariable String id, @RequestBody @Valid UpdateTeacherDto updateTeacherDto) {
        return this.teacherService.updateTeacher(id, updateTeacherDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable String id) {
        this.studyingService.deleteTeacherWithStudyings(id);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('director')")
    @GetMapping("/inSchool/{schoolId}")
    public List<TeacherDto> getTeachersInSchool(@PathVariable String schoolId) {
        return this.teacherService.getTeachersInSchool(schoolId);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/qualification")
    public CreateQualificationDto createTeacherQualification(@RequestBody @Valid CreateQualificationDto createQualificationDto) {
        return qualificationService.createQualification(createQualificationDto);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
    @GetMapping("/qualification/{teacherId}")
    public List<SubjectDto> getTeacherQualifications(@PathVariable String teacherId) {
        return qualificationService.getAllTeacherQualifications(teacherId);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/qualification/{teacherId}/{subjectId}")
    public void deleteTeacherQualification(@PathVariable String teacherId, @PathVariable String subjectId) {
        qualificationService.deleteQualification(teacherId, subjectId);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/qualification/{teacherId}")
    public void deleteTeacherQualifications(@PathVariable String teacherId) {
        qualificationService.deleteAllTeacherQualifications(teacherId);
    }
}
