package com.informatics.e_school_journal.dto.student;

import com.informatics.e_school_journal.dto.user.CreateUserDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateStudentDto {
    private CreateUserDto createUserDto;
}
