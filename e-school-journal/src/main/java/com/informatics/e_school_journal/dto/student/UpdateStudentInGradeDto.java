package com.informatics.e_school_journal.dto.student;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateStudentInGradeDto {
    private String keycloakId;
    private Long gradeId;
}
