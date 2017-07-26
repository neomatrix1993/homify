package com.homify.dao;

import com.homify.bo.CustomerBo;
import com.homify.entities.CustomerEntity;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * The customer dao implementation
 * Created by swapnil.gupta on 3/25/17.
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public CustomerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public CustomerEntity add(CustomerBo customerBo) {
        CustomerEntity customerEntity = getCustomerEntity(customerBo);
        try {
            sessionFactory.getCurrentSession().save(customerEntity);
        } catch (Exception e) {
            LOGGER.error("Error during persisting.", e);
            return null;
        }

        return customerEntity;
    }

    @Override
    @Transactional
    public CustomerEntity get(int id) {
        try {
            return (CustomerEntity) sessionFactory.getCurrentSession().get(CustomerEntity.class, id);
        } catch (Exception e) {
            LOGGER.error("Error during fetching.", e);
            return new CustomerEntity();
        }
    }

    private CustomerEntity getCustomerEntity(CustomerBo customerBo) {
        return CustomerEntity.builder()
                .fullName(customerBo.getFullName())
                .email(customerBo.getEmail())
                .build();
    }
}
