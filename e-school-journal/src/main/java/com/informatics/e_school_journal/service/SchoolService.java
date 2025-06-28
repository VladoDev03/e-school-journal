package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.school.CreateSchoolDto;
import com.informatics.e_school_journal.dto.school.SchoolDto;
import com.informatics.e_school_journal.dto.school.UpdateSchoolDto;
import java.util.List;

public interface SchoolService {
    SchoolDto createSchool(CreateSchoolDto createSchoolDto);

    SchoolDto updateSchool(String id, UpdateSchoolDto updateSchoolDto);

    SchoolDto getSchoolById(String id);

    List<SchoolDto> getAllSchools();

    void deleteSchool(String id);
}
