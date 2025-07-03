package com.informatics.e_school_journal.dto.subject;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubjectAvgMarkDto {
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters.")
    private String subjectName;
    @Min(value = 2)
    @Max(value = 6)
    private double avgMark;
}
