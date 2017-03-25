package com.homify.services;

import com.homify.bo.CustomerBo;
import org.springframework.stereotype.Service;

/**
 * Customer Service Interface
 * Created by swapnil.gupta on 3/25/17.
 */
@Service
public interface CustomerService {

    CustomerBo createCustomer(CustomerBo customerBo);

    CustomerBo getCustomer(int id);
}
