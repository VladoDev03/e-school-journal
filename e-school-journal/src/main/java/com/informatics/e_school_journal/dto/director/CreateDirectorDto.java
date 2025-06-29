package com.informatics.e_school_journal.dto.director;

import com.informatics.e_school_journal.dto.user.CreateUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateDirectorDto {
    private CreateUserDto createUserDto;
    private String schoolId;
}
