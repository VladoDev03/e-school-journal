package com.informatics.e_school_journal.dto.studying;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateStudyingDto {
    private Long gradeId;
    private Long teacherId;
    private Long subjectId;
}
