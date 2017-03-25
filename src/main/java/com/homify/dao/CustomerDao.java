package com.homify.dao;

import com.homify.bo.CustomerBo;
import com.homify.entities.CustomerEntity;

/**
 * Customer DAO interface
 * Created by swapnil.gupta on 3/25/17.
 */
public interface CustomerDao {

    CustomerEntity add(CustomerBo customerBo);

    CustomerEntity get(int id);
}
