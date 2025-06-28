package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Admin extends BaseEntity {
}
