package com.informatics.e_school_journal.dto.mark;

import com.informatics.e_school_journal.data.enums.MarkType;
import com.informatics.e_school_journal.data.enums.Term;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateMarkDto {
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
}
