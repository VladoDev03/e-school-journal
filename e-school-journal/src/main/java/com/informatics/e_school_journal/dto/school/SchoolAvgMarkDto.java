package com.informatics.e_school_journal.dto.school;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchoolAvgMarkDto {
    private String schoolName;
    private double avgMark;
}
