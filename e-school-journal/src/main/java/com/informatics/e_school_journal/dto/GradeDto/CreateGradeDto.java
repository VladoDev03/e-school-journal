package com.informatics.e_school_journal.dto.GradeDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateGradeDto {
    private int grade;
    private String stream;
    private Long schoolId;
}
