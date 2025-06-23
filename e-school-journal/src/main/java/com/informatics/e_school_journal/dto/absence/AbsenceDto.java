package com.informatics.e_school_journal.dto.absence;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AbsenceDto {
    private long id;
    private LocalDateTime date;
    private boolean isExcused;
    private long studentId;
    private long teacherId;
    private long studyingId;
}
