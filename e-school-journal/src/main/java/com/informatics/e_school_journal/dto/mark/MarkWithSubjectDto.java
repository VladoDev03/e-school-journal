package com.informatics.e_school_journal.dto.mark;

import com.informatics.e_school_journal.data.enums.MarkType;
import com.informatics.e_school_journal.data.enums.Term;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MarkWithSubjectDto {
    private String id;
    @NotNull
    @Min(value = 2)
    @Max(value = 6)
    private double mark;

    @NotNull
    private MarkType markType;

    @NotNull
    private Term term;

    @NotNull
    private String studyingId;

    @NotNull
    private String studentId;

    @Size(min = 1, max = 45, message = "Name must be between 1 and 45 characters.")
    private String subjectName;
}
