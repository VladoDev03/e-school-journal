package com.informatics.e_school_journal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informatics.e_school_journal.config.SecurityConfig;
import com.informatics.e_school_journal.dto.student.*;
import com.informatics.e_school_journal.service.StudentGradeService;
import com.informatics.e_school_journal.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@EnableMethodSecurity(prePostEnabled = true)
@WebMvcTest(controllers = StudentController.class)
@ContextConfiguration(classes = {StudentController.class, SecurityConfig.class})
class StudentControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockitoBean private StudentService studentService;
    @MockitoBean private StudentGradeService studentGradeService;
    @MockitoBean private JwtDecoder jwtDecoder;
    @MockitoBean private JwtAuthenticationConverter jwtAuthenticationConverter;
    @MockitoBean private SecurityFilterChain securityFilterChain;

    @Test
    @WithMockUser(authorities = {"admin"})
    void getAllStudents_returnsList_test() throws Exception {
        StudentDto dto = new StudentDto();
        dto.setId("123");

        given(studentService.getStudents()).willReturn(List.of(dto));

        mockMvc.perform(get("/api/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("123")));
    }

    @Test
    @WithMockUser(authorities = {"teacher"})
    void getStudentById_returnsDto_test() throws Exception {
        StudentDto dto = new StudentDto();
        dto.setId("stu1");

        given(studentService.getStudentById("stu1")).willReturn(dto);

        mockMvc.perform(get("/api/student/stu1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("stu1")));
    }

    @Test
    @WithMockUser(authorities = {"parent"})
    void getStudentsByParent_returnsList_test() throws Exception {
        StudentPersonalInfoDto student = new StudentPersonalInfoDto("1", "Mariya", "Ivanova");

        given(studentService.getStudentsByParentId("p1")).willReturn(List.of(student));

        mockMvc.perform(get("/api/student/parent/p1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Mariya")));
    }

    @Test
    @WithMockUser(authorities = {"director"})
    void getStudentsByDirector_returnsList_test() throws Exception {
        StudentPersonalInfoDto student = new StudentPersonalInfoDto("2", "Alex", "Milanov");

        given(studentService.getStudentsByDirectorId()).willReturn(List.of(student));

        mockMvc.perform(get("/api/student/director"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Alex")));
    }

    @Test
    @WithMockUser(authorities = {"admin"})
    void deleteStudent_worksWithoutContent_test() throws Exception {
        mockMvc.perform(delete("/api/student/abc123"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"admin"})
    void enrollStudentInGrade_returnsResponse_test() throws Exception {
        StudentInGradeDto dto = new StudentInGradeDto("stu1", "grade5");
        given(studentGradeService.enrollStudentInGrade("stu1", "grade5")).willReturn(dto);

        mockMvc.perform(put("/api/student/stu1/grade-id/grade5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("stu1")));
    }

    @Test
    @WithMockUser(authorities = {"admin"})
    void withdrawStudentFromGrade_returnsResponse_test() throws Exception {
        StudentInGradeDto dto = new StudentInGradeDto("stu1", null);
        given(studentGradeService.withdrawStudentFromGrade("stu1")).willReturn(dto);

        mockMvc.perform(put("/api/student/withdraw/stu1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("stu1")));
    }

}
