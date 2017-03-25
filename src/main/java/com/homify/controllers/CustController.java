package com.homify.controllers;

import com.homify.bo.BaseResponse;
import com.homify.bo.CustomerBo;
import com.homify.services.CustomerService;
import com.homify.validations.CustomerValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * The customer controller
 * Created by swapnil.gupta on 3/25/17.
 */
@RestController
@RequestMapping(value = "/customerservice/customer")
public class CustController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustController.class);

    @Autowired
    private CustomerService customerService;

    /**
     * REST point to add a new customer.
     * URL: "/customerservice/customer/"
     *
     * @param customerBo New customer object
     * @return Base response
     */
    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse create(@RequestBody CustomerBo customerBo) {
        LOGGER.info("Adding customer details for id: ");

        BaseResponse invalidResponse = CustomerValidation.validateRequest(customerBo);
        if (invalidResponse != null)
            return invalidResponse;

        CustomerBo addCustomer = customerService.createCustomer(customerBo);

        return new BaseResponse(200, "Success", addCustomer);

    }

    /**
     * Rest point to fetch the customer details.
     * URL: "customerservice/customer?customer_id=1"
     *
     * @param id The customer id
     * @return Base response
     */
    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse get(@RequestParam(value = "customer_id") Integer id) {
        LOGGER.info("Fetching customer details for id: " + id);
        CustomerBo customerBo = customerService.getCustomer(id);

        return new BaseResponse(200, "Success", customerBo);
    }
}
