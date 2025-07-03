package com.informatics.e_school_journal.dto.teacher;

import com.informatics.e_school_journal.dto.user.CreateUserDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateTeacherDto {
    @NotNull
    private CreateUserDto createUserDto;
}
