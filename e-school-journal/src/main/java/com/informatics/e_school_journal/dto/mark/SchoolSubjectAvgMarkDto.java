package com.informatics.e_school_journal.dto.mark;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchoolSubjectAvgMarkDto {
    private String schoolName;
    private String subjectName;
    private double avgMark;
}
