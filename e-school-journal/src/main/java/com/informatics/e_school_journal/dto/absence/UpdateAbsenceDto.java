package com.informatics.e_school_journal.dto.absence;

import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateAbsenceDto {
    private LocalDateTime date;
    private boolean isExcused;
    private String studentId;
    private String teacherId;
    private String studyingId;
}
