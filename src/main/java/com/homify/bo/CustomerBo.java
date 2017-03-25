package com.homify.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.homify.entities.ContractEntity;
import lombok.*;

import java.util.Set;

/**
 * The customer response to be returned.
 * Created by swapnil.gupta on 3/25/17.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerBo {
    private int id;
    private String fullName;
    private String email;
    private Set<ContractEntity> contracts;
}
