package com.homify.bo;

import lombok.Getter;
import lombok.ToString;

/**
 * Contract types
 * Created by swapnil.gupta on 3/25/17.
 */
@Getter
@ToString
public enum ContractType {
    VALID("VALID"),
    VOIDABLE("VOIDABLE"),
    VOID("VOID");

    private String text;

    ContractType(String text) {
        this.text = text;
    }

    public static ContractType fromString(String text) {
        for (ContractType b : ContractType.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
