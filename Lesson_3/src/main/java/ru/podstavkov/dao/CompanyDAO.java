package ru.podstavkov.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.podstavkov.entity.Company;

@Component
@Transactional
public class CompanyDAO extends AbstractDAO {

    public List<Company> getListCompany() {
        return em.createQuery("SELECT e FROM Company e", Company.class).getResultList();
    }

    public Company merge(Company company) {
        return em.merge(company);
    }
}
