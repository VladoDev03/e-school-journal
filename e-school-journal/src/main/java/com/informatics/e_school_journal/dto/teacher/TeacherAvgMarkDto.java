package com.informatics.e_school_journal.dto.teacher;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherAvgMarkDto {
    private String teacherId;
    private double avgMark;
}
