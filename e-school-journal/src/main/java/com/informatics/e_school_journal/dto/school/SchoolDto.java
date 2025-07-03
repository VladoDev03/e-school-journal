package com.informatics.e_school_journal.dto.school;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchoolDto {
    private String id;
    @NotNull
    @Size(min = 1, max = 90, message = "Name must be between 1 and 90 characters.")
    private String name;

    @NotNull
    private String address;
    private String directorId;
}
