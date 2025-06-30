package com.informatics.e_school_journal.dto.absence;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateAbsenceDto {
    private LocalDate date;
    private boolean isExcused;
    private String studentId;
    private String studyingId;
}
