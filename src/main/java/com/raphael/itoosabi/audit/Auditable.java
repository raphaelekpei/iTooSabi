package com.raphael.itoosabi.audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
@Getter
@Setter
public class Auditable {

    // this class stores the audit information
    // Any entity we would like to audit will extend this class
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedBy
    @Column(name = "modified_by")
    private String lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "modified_date")
    private LocalDateTime lastModifiedAt;

}
