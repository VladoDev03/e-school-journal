package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.school.CreateSchoolDto;
import com.informatics.e_school_journal.dto.school.SchoolDto;
import com.informatics.e_school_journal.dto.school.UpdateSchoolDto;
import java.util.List;

public interface SchoolService {
    SchoolDto createSchool(CreateSchoolDto createSchoolDto);

    SchoolDto updateSchool(long id, UpdateSchoolDto updateSchoolDto);

    SchoolDto getSchoolById(long id);

    List<SchoolDto> getAllSchools();

    void deleteSchool(long id);
}
