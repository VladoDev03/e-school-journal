package com.informatics.e_school_journal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserInfoDto {
    private String id;
    private String name;
    private List<String> roles;
}
