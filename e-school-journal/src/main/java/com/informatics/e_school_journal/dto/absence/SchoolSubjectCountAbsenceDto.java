package com.informatics.e_school_journal.dto.absence;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchoolSubjectCountAbsenceDto {
    private String schoolName;
    private String subjectName;
    private int countAbsence;
}

