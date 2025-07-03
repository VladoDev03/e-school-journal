package com.informatics.e_school_journal.dto.studying;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateStudyingDto {
    @NotNull
    private String gradeId;
    @NotNull
    private String teacherId;
    @NotNull
    private String subjectId;
}
