package com.homify.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Contract Entity
 * Created by swapnil.gupta on 3/25/17.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "contracts")
public class ContractEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "revenue", nullable = false)
    private double revenue;
}
