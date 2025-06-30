package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.mark.*;
import com.informatics.e_school_journal.dto.school.SchoolAvgMarkDto;
import com.informatics.e_school_journal.dto.subject.SubjectAvgMarkDto;
import com.informatics.e_school_journal.dto.teacher.TeacherAvgMarkDto;

import java.util.List;

public interface MarkService {
    MarkDto createMark(CreateMarkDto createMarkDto);
    MarkDto updateMark(String id, UpdateMarkDto updateMarkDto);
    void deleteMark(String id);
    MarkWithSubjectDto getMark(String id);
    List<MarkWithSubjectDto> getMarksByStudent(String studentId);
    List<TeacherAvgMarkDto> getAvgMarksByTeacherByDirector();
    List<SubjectAvgMarkDto> getAvgMarksBySubjectByDirector();
    SchoolAvgMarkDto getAvgMarkBySchoolByDirector();
    List<SubjectAvgMarkDto> getAvgMarksBySubject();
    List<SchoolAvgMarkDto> getAvgMarkBySchool();
    List<SchoolSubjectAvgMarkDto> getAvgMarkBySchoolAndSubject();

}
