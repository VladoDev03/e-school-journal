package com.informatics.e_school_journal.dto.qualification;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateQualificationDto {
    private String teacherId;
    private List<String> subjectIds;
}
