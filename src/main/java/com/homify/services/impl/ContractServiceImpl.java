package com.homify.services.impl;

import com.homify.bo.BaseResponse;
import com.homify.bo.ContractBo;
import com.homify.bo.ContractType;
import com.homify.dao.ContractDao;
import com.homify.entities.ContractEntity;
import com.homify.services.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

/**
 * The contract service implementation.
 * Created by swapnil.gupta on 3/25/17.
 */
@Service
public class ContractServiceImpl implements ContractService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractServiceImpl.class);

    private ContractDao contractDao;

    @Autowired
    public ContractServiceImpl(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    @Override
    public ContractBo addContract(ContractBo contractBo) {
        LOGGER.info("Service layer: add Contract processing.");
        ContractEntity contractEntity = contractDao.add(contractBo);
        return buildContractResponse.apply(contractEntity);
    }

    @Override
    public BaseResponse getRevenueByCustomerId(int id) {
        LOGGER.info("Service layer: getRevenueByCustomerId processing.");
        List<ContractEntity> contractEntities = contractDao.getByCustomerId(id);
        if (contractEntities.isEmpty())
            return new BaseResponse(400, "Bad Request", "Customer id doesn't exist");

        Double revenue = contractEntities.stream().mapToDouble(ContractEntity::getRevenue).sum();
        return new BaseResponse(200, "Success", revenue);
    }

    @Override
    public BaseResponse getRevenueByContractType(ContractType contractType) {
        List<ContractEntity> contractEntities = contractDao.getByType(contractType);
        if (contractEntities.isEmpty())
            return new BaseResponse(400, "Bad Request", "Customer id doesn't exist");

        Double revenue = contractEntities.stream().mapToDouble(ContractEntity::getRevenue).sum();
        return new BaseResponse(200, "Success", revenue);
    }

    /**
     * Build Contract response given contract input as entity.
     */
    Function<ContractEntity, ContractBo> buildContractResponse = contractEntity -> ContractBo.builder()
            .id(contractEntity.getId())
            .customerId(contractEntity.getCustomerId())
            .startDate(contractEntity.getStartDate())
            .revenue(contractEntity.getRevenue())
            .type(contractEntity.getType())
            .build();
}
