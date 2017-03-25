package com.homify.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * The base entity to be extended in other classes having common properties.
 * Created by swapnil.gupta on 3/25/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
abstract class BaseEntity {

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
    @Column(name = "soft_deleted", nullable = false)
    private boolean softDeleted;
}
