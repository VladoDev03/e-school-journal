package com.informatics.e_school_journal.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CredentialsDto {
    private final boolean temporary;
    private final String type;
    @Setter
    private String value;

    public CredentialsDto(String value) {
        this.temporary = false;
        this.type = "password";
        this.value = value;
    }

}
