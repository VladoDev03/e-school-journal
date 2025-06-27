package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.mark.CreateMarkDto;
import com.informatics.e_school_journal.dto.mark.MarkDto;
import com.informatics.e_school_journal.dto.mark.MarkWithSubjectDto;
import com.informatics.e_school_journal.dto.mark.UpdateMarkDto;

import java.util.List;

public interface MarkService {
    MarkDto createMark(CreateMarkDto createMarkDto);
    MarkDto updateMark(Long id, UpdateMarkDto updateMarkDto);
    void deleteMark(Long id, Long teacherId);
    MarkWithSubjectDto getMark(Long id);
    List<MarkWithSubjectDto> getMarksByStudent(Long studentId);

}
