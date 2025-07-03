package com.informatics.e_school_journal.dto.grade;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateGradeDto {
    @NotNull
    @Min(value = 1)
    @Max(value = 12)
    private int grade;

    @NotNull
    @Positive
    private int year;

    @NotNull
    @Size(min = 1, max = 45, message = "Stream must be between 1 and 45 characters.")
    private String stream;

    @NotNull
    private String schoolId;
}
