package com.homify.dao;

import com.homify.bo.ContractBo;
import com.homify.bo.ContractType;
import com.homify.entities.ContractEntity;

import java.util.List;

/**
 * Contract DAO interface.
 * Created by swapnil.gupta on 3/25/17.
 */
public interface ContractDao {

    ContractEntity add(ContractBo contractBo);

    List<ContractEntity> getByCustomerId(int id);

    List<ContractEntity> getByType(ContractType type);
}
