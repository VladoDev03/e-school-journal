package com.informatics.e_school_journal.dto.school;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchoolAvgMarkDto {
    @NotNull
    @Size(min = 1, max = 90, message = "Name must be between 1 and 90 characters.")
    private String schoolName;

    @NotNull
    @Min(value = 2)
    @Max(value = 6)
    private double avgMark;
}
