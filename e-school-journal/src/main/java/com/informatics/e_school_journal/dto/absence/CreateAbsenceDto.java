package com.informatics.e_school_journal.dto.absence;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateAbsenceDto {
    @PastOrPresent
    @NotNull
    private LocalDate date;

    @NotNull
    private boolean isExcused;

    @NotNull
    private String studentId;

    @NotNull
    private String studyingId;
}
