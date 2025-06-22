package com.informatics.e_school_journal.dto.qualification;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateQualificationDto {
    private long teacherId;
    private List<Long> subjectIds;
}
