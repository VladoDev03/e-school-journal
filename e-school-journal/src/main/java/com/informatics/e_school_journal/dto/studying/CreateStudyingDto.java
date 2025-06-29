package com.informatics.e_school_journal.dto.studying;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateStudyingDto {
    private String gradeId;
    private String teacherId;
    private String subjectId;
}
