package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Director extends BaseEntity{
    @OneToOne(fetch = FetchType.LAZY)
    private School school;
}
