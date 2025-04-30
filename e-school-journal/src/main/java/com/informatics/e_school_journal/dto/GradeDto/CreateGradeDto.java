package com.informatics.e_school_journal.dto.GradeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateGradeDto {
    private int grade;
    private String stream;
    private Long schoolId;
}
