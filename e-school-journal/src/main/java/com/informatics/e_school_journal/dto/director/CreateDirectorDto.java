package com.informatics.e_school_journal.dto.director;

import com.informatics.e_school_journal.dto.user.CreateUserDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateDirectorDto {
    @NotNull
    private CreateUserDto createUserDto;
    private String schoolId;
}
