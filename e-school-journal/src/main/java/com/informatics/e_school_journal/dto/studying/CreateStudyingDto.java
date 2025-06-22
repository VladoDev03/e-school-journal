package com.informatics.e_school_journal.dto.studying;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateStudyingDto {
    private Long gradeId;
    private Long teacherId;
    private Long subjectId;
}
