package com.informatics.e_school_journal.dto.mark;

import com.informatics.e_school_journal.data.enums.MarkType;
import com.informatics.e_school_journal.data.enums.Term;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MarkWithSubjectDto {
    private String id;
    private double mark;
    private MarkType markType;
    private Term term;
    private String studyingId;
    private String studentId;
    private String subjectName;
}
