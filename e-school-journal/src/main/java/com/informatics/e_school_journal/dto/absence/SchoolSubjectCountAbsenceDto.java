package com.informatics.e_school_journal.dto.absence;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchoolSubjectCountAbsenceDto {
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters.")
    private String schoolName;

    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters.")
    private String subjectName;

    @PositiveOrZero
    private int countAbsence;
}

