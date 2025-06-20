package com.informatics.e_school_journal.dto.schoolDto;

import com.informatics.e_school_journal.data.entity.Director;
import com.informatics.e_school_journal.data.entity.Grade;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateSchoolDto {
    private String name;
    private String address;
    private long directorId;

}