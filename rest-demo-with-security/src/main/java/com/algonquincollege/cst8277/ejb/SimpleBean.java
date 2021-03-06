package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.PU_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.algonquincollege.cst8277.models.Employee;

@Stateless
public class SimpleBean {

    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    public List<Employee> getEmployeeList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        cq.select(cq.from(Employee.class));
        return em.createQuery(cq).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateEmployee(Employee empWithUpdatedFields) {
        em.merge(empWithUpdatedFields);
    }

    public Employee getEmployeeById(int id) {
        return em.find(Employee.class, id);
    }
}