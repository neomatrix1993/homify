package com.homify.dao;

import com.homify.bo.ContractBo;
import com.homify.bo.ContractType;
import com.homify.entities.ContractEntity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

/**
 * The contract dao implementation
 * Created by swapnil.gupta on 3/25/17.
 */
@Repository
public class ContractDaoImpl implements ContractDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContractDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public ContractDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public ContractEntity add(ContractBo contractBo) {
        ContractEntity contractEntity = buildContractEntity.apply(contractBo);
        try {
            sessionFactory.getCurrentSession().save(contractEntity);
        } catch (Exception e) {
            LOGGER.error("Error during persisting.", e);
            return null;
        }
        return contractEntity;
    }

    @Override
    public List<ContractEntity> getByCustomerId(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ContractEntity.class);
        criteria.add(Restrictions.eq("customerId", id));
        criteria.add(Restrictions.eq("softDeleted", false));
        return criteria.list();
    }

    @Override
    public List<ContractEntity> getByType(ContractType contractType) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ContractEntity.class);
        criteria.add(Restrictions.eq("type", contractType.toString()));
        criteria.add(Restrictions.eq("softDeleted", false));
        return criteria.list();
    }

    /**
     * Build Contract response given contract input as entity.
     */
    Function<ContractBo, ContractEntity> buildContractEntity = contractResponse -> ContractEntity.builder()
            .customerId(contractResponse.getCustomerId())
            .startDate(contractResponse.getStartDate())
            .revenue(contractResponse.getRevenue())
            .type(contractResponse.getType())
            .build();
}
