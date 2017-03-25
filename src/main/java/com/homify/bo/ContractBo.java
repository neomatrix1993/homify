package com.homify.bo;

import lombok.*;

import javax.validation.constraints.Min;
import java.sql.Timestamp;

/**
 * The contract response to be returned
 * Created by swapnil.gupta on 3/25/17.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ContractBo {
    private int id;
    @Min(value = 1)
    private int customerId;
    private Timestamp startDate;
    private String type;
    private double revenue;
}
