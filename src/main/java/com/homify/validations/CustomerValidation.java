package com.homify.validations;

import com.homify.bo.BaseResponse;
import com.homify.bo.CustomerBo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Customer validation class.
 * Created by swapnil.gupta on 3/26/17.
 */
public class CustomerValidation {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_NAME_REGEX =
            Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);

    public static BaseResponse validateRequest(CustomerBo customerBo) {
        List<String> errors = new ArrayList<>();

        if (!VALID_NAME_REGEX.matcher(customerBo.getFullName()).find()) {
            errors.add("NAME INVALID");
        }
        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(customerBo.getEmail()).find()) {
            errors.add("EMAIL INVALID");
        }

        if (!errors.isEmpty())
            return new BaseResponse(400, "Bad Request",
                    "Please check the errors: " + errors.stream().collect(Collectors.joining(", ")));

        return null;
    }

}
