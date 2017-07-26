package com.homify.controllers;

import com.homify.bo.BaseResponse;
import com.homify.bo.ContractBo;
import com.homify.bo.ContractType;
import com.homify.services.ContractService;
import com.homify.validations.ContractValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * The Contract controller
 * Created by swapnil.gupta on 3/25/17.
 */
@RestController
@RequestMapping("/customerservice/contract")
public class ContractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractController.class);

    private static final String SUCCESS = "SUCCESS";

    private ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse add(@RequestBody ContractBo contractBo) {
        LOGGER.info("Adding customer details for id: ");
        BaseResponse invalidResponse = ContractValidation.validateRequest(contractBo);
        if (invalidResponse != null)
            return invalidResponse;

        ContractBo response = contractService.addContract(contractBo);

        return new BaseResponse(200, SUCCESS, response);
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getCustomerRevenue(@RequestParam(value = "customer_id") Integer id) {
        LOGGER.info("Fetching customer details for id: " + id);

        return contractService.getRevenueByCustomerId(id);
    }

    @RequestMapping(value = "/type",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getTypeRevenue(@RequestParam(value = "type") String type) {
        LOGGER.info("Fetching customer details for id: " + type);

        if (!ContractValidation.validContractType(type)) {
            return new BaseResponse(400, "Bad Request", "Please enter correct contract type.");
        }

        return contractService.getRevenueByContractType(ContractType.fromString(type));
    }
}
