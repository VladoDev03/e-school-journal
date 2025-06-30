package com.informatics.e_school_journal.dto.absence;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AbsenceWithSubjectDto {
    private String id;
    private LocalDate date;
    private boolean isExcused;
    private String studentId;
    private String studyingId;
    private String subjectName;
}
