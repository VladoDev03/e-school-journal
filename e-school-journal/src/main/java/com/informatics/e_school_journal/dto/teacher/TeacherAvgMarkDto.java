package com.informatics.e_school_journal.dto.teacher;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherAvgMarkDto {
    private String teacherId;
    @Min(value = 2)
    @Max(value = 6)
    private double avgMark;
}
