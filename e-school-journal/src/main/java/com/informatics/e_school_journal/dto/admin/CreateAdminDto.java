package com.informatics.e_school_journal.dto.admin;

import com.informatics.e_school_journal.dto.user.CreateUserDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateAdminDto {
    private CreateUserDto createUserDto;
}
