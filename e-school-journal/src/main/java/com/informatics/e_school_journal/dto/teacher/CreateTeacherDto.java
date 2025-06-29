package com.informatics.e_school_journal.dto.teacher;

import com.informatics.e_school_journal.dto.user.CreateUserDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateTeacherDto {
    private CreateUserDto createUserDto;
}
