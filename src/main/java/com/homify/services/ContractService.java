package com.homify.services;

import com.homify.bo.BaseResponse;
import com.homify.bo.ContractBo;
import com.homify.bo.ContractType;

/**
 * Contract Service class
 * Created by swapnil.gupta on 3/25/17.
 */
public interface ContractService {

    ContractBo addContract(ContractBo contractBo);

    BaseResponse getRevenueByCustomerId(int id);

    BaseResponse getRevenueByContractType(ContractType contractType);

}
