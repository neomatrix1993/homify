package com.homify.services.impl;

import com.homify.bo.CustomerBo;
import com.homify.dao.CustomerDao;
import com.homify.entities.CustomerEntity;
import com.homify.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;

/**
 * The customer service implementation.
 * Created by swapnil.gupta on 3/25/17.
 */
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * Create Customer
     *
     * @param customerBo Customer object
     * @return CustomerBo
     */
    @Override
    public CustomerBo createCustomer(CustomerBo customerBo) {
        CustomerEntity customerEntity = customerDao.add(customerBo);
        return buildCustomerResponse.apply(customerEntity);
    }

    /**
     * @param id Customer Id
     * @return CustomerBo
     */
    @Override
    public CustomerBo getCustomer(int id) {
        CustomerEntity customerEntity = customerDao.get(id);
        return buildCustomerResponse.apply(customerEntity);
    }

    /**
     * Build customer response given customer input as entity.
     */
    Function<CustomerEntity, CustomerBo> buildCustomerResponse = customerEntity -> CustomerBo.builder()
            .id(customerEntity.getId())
            .fullName(customerEntity.getFullName())
            .email(customerEntity.getEmail())
            .contracts(customerEntity.getContracts())
            .build();
}
