package com.informatics.e_school_journal.dto.qualification;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateQualificationDto {
    @NotNull
    private String teacherId;
    @NotEmpty
    private List<String> subjectIds;
}
