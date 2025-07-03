package com.informatics.e_school_journal.dto.student;

import com.informatics.e_school_journal.dto.user.CreateUserDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateStudentDto {
    @NotNull
    private CreateUserDto createUserDto;
}
