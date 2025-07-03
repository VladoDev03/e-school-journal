package com.informatics.e_school_journal.repository;

import com.informatics.e_school_journal.data.entity.Student;
import com.informatics.e_school_journal.data.entity.Parent;
import com.informatics.e_school_journal.data.repo.ParentRepository;
import com.informatics.e_school_journal.data.repo.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Test
    @DisplayName("Should find students by parent ID")
    void shouldFindStudentsByParentId() {
        Student student1 = new Student("s1");
        Student student2 = new Student("s2");
        studentRepository.saveAll(Arrays.asList(student1, student2));

        Parent parent = new Parent("parent1");
        parent.setChildren(Set.of(student1, student2));
        parentRepository.save(parent);

        List<Student> students = studentRepository.findStudentsByParentsId("parent1");
        assertEquals(2, students.size());
        assertTrue(students.stream().anyMatch(s -> s.getId().equals("s1")));
        assertTrue(students.stream().anyMatch(s -> s.getId().equals("s2")));
    }

    @Test
    @DisplayName("Should return empty list when no students match parent ID")
    void shouldNotFindStudentsByParentId() {
        List<Student> students = studentRepository.findStudentsByParentsId("nonexistent");
        assertTrue(students.isEmpty());
    }

}
