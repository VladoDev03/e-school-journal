package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Admin extends BaseEntity {
    public Admin(String id) {
        super(id);
    }
}
