package com.informatics.e_school_journal.dto.mark;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchoolSubjectAvgMarkDto {
    @Size(min = 1, max = 90, message = "Name must be between 1 and 45 characters.")
    private String schoolName;

    @Size(min = 1, max = 45, message = "Name must be between 1 and 45 characters.")
    private String subjectName;

    @Min(value = 2)
    @Min(value = 6)
    private double avgMark;
}
