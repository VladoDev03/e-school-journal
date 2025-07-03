package com.informatics.e_school_journal.dto.parent;

import com.informatics.e_school_journal.dto.user.CreateUserDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateParentDto {
    @NotNull
    private CreateUserDto createUserDto;
    @NotNull
    private String studentEmail;
}
