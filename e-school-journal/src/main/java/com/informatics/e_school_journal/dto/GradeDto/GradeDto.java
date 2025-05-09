package com.informatics.e_school_journal.dto.GradeDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GradeDto {
    private Long id;
    private int grade;
    private String stream;
    private Long schoolId;
}
