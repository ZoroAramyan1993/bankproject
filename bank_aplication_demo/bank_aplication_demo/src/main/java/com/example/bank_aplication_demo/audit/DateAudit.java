package com.example.bank_aplication_demo.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;


@MappedSuperclass
@JsonIgnoreProperties(value = {"created",
        "updated"})
@EntityListeners(AuditingEntityListener.class)
public class DateAudit {

    @Column(nullable = false,updatable = false)
    @CreatedDate
    private Instant created;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant updated;


    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }
}
