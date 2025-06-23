package com.informatics.e_school_journal.dto.mark;

import com.informatics.e_school_journal.data.enums.MarkType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MarkWIthSubjectDto {
    private Long id;
    private double mark;
    private MarkType markType;
    private Long studyingId;
    private Long studentId;
    private String subjectName;
}
