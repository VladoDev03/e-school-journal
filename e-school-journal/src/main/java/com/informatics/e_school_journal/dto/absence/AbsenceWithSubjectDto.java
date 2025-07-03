package com.informatics.e_school_journal.dto.absence;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AbsenceWithSubjectDto {
    private String id;
    @PastOrPresent
    private LocalDate date;

    @NotNull
    private boolean isExcused;

    @NotNull
    private String studentId;

    @NotNull
    private String studyingId;

    @NotNull
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters.")
    private String subjectName;
}
