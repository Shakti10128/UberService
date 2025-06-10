package org.shakti.uberreviewservice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

//enable jpa to automatically add the createdAt & updatedAt timestamps
//also add the @EnableJpaAuditing in main spring boot file to work this
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass // the annotation tell to JPA that this is not a separate class for the table in DB
// all the properties will be part of the child class
@Getter
@Setter
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(nullable = false)
    protected Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(nullable = false)
    protected Date updatedAt;
}
