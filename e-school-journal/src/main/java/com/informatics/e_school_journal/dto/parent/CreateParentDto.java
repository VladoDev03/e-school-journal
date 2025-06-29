package com.informatics.e_school_journal.dto.parent;

import com.informatics.e_school_journal.dto.user.CreateUserDto;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateParentDto {
    private CreateUserDto createUserDto;
    private Set<String> childrenIds;
}
