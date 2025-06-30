package com.informatics.e_school_journal.dto.subject;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubjectAvgMarkDto {
    private String subjectName;
    private double avgMark;
}
