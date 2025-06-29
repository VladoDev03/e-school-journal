package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.util.UUID;

@MappedSuperclass
@Getter
public class BaseEntity {
    @Id
    private String id;

    public BaseEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public BaseEntity(String id) {
        this.id = id;
    }
}
