package com.informatics.e_school_journal.dto.subject;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateSubjectDto {
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters.")
    private String name;
}
