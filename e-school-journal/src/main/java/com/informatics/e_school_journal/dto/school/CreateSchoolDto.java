package com.informatics.e_school_journal.dto.school;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CreateSchoolDto {
    @NotNull
    @Size(min = 1, max = 90, message = "Name must be between 1 and 90 characters.")
    private String name;

    @NotNull
    private String address;
}
