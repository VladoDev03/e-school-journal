package com.informatics.e_school_journal.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CreateUserDto {
    @Setter
    private List<CredentialsDto> credentials;
    @Setter
    private String username;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private String email;
    private final boolean emailVerified;
    private final boolean enabled;

    public CreateUserDto(CredentialsDto credentials, String username, String firstName, String lastName, String email) {
        List<CredentialsDto> credentialsDtos = new ArrayList<>();
        credentialsDtos.add(credentials);
        this.credentials = credentialsDtos;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.emailVerified = true;
        this.enabled = true;
    }
}
