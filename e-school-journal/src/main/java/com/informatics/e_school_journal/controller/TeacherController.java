package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.qualification.CreateQualificationDto;
import com.informatics.e_school_journal.dto.teacher.CreateTeacherDto;
import com.informatics.e_school_journal.dto.teacher.TeacherDto;
import com.informatics.e_school_journal.dto.teacher.UpdateTeacherDto;
import com.informatics.e_school_journal.service.QualificationService;
import com.informatics.e_school_journal.service.TeacherService;
import com.informatics.e_school_journal.service.TeacherStudyingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableMethodSecurity(prePostEnabled = true)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    private final QualificationService qualificationService;
    private final TeacherStudyingService teacherStudyingService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public TeacherDto createTeacher(@RequestBody CreateTeacherDto createTeacherDto) {
        return this.teacherService.createTeacher(createTeacherDto);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public TeacherDto getTeacherById(@PathVariable long id) {
        return this.teacherService.getTeacherById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public List<TeacherDto> getTeachers() {
        return this.teacherService.getTeachers();
    }

    @PutMapping("/{id}")
    public TeacherDto updateTeacher(@PathVariable Long id, @RequestBody UpdateTeacherDto updateTeacherDto) {
        return this.teacherService.updateTeacher(id, updateTeacherDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable long id) {
        this.teacherStudyingService.deleteTeacherWithStudyings(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/inSchool/{schoolId}")
    public List<TeacherDto> getTeachersInSchool(@PathVariable Long schoolId) {
        return this.teacherService.getTeachersInSchool(schoolId);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/qualification")
    public CreateQualificationDto createTeacherQualification(@RequestBody CreateQualificationDto createQualificationDto) {
        return qualificationService.createQualification(createQualificationDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/qualification/{teacherId}/{subjectId}")
    public void deleteTeacherQualification(@PathVariable Long teacherId, @PathVariable Long subjectId) {
        qualificationService.deleteQualification(teacherId, subjectId);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/qualification/{teacherId}")
    public void deleteTeacherQualification(@PathVariable Long teacherId) {
        qualificationService.deleteAllTeacherQualifications(teacherId);
    }
}
