package com.informatics.e_school_journal.dto.subject;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubjectDto {
    private String id;
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters.")
    private String name;
}
