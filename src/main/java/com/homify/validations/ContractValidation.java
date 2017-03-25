package com.homify.validations;

import com.homify.bo.BaseResponse;
import com.homify.bo.ContractBo;
import com.homify.bo.ContractType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contract validation class.
 * Created by swapnil.gupta on 3/26/17.
 */
public class ContractValidation {
    private ContractValidation() {
    }

    public static boolean validContractType(String type) {
        return ContractType.fromString(type) != null;
    }

    public static BaseResponse validateRequest(ContractBo contractBo) {
        List<String> errors = new ArrayList<>();

        if (ContractType.fromString(contractBo.getType()) == null) {
            errors.add("TYPE mismatch");
        }

        if (!errors.isEmpty())
            return new BaseResponse(400, "Bad Request",
                    "Please check the errors: " + errors.stream().collect(Collectors.joining(", ")));

        return null;
    }
}
