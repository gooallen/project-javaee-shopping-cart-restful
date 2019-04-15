/**
 * File: MemberBean.java
 * Course: CST8277
 * @author: Byeongyun Goo (#040888224), Zeyang Hu (#040885680), Sohaila Binte Ridwan (#040847430)
 * @date: April 13th, 2019
 */
package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.PU_NAME;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.algonquincollege.cst8277.models.Employee;
import com.algonquincollege.cst8277.models.LineItem;
import com.algonquincollege.cst8277.models.Member;
import com.algonquincollege.cst8277.models.Member_;

@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 20)
public class MemberBean {
  
    /**
     * initializing an entityManager
     */
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    /**
     * deafult constructor
     */
    public MemberBean() {
        //default construtor
    }    
    
    /**
     * get a list of members
     * @return the list of members
     */
    public List<Member> getMemberList() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);
        Root<Member> member = cq.from(Member.class);
        cq.select(member);
        TypedQuery<Member> typedQuery = em.createQuery(cq);
        List<Member> result = typedQuery.getResultList();
        
        
        return result;
        
    }
    
    /**
     * create a new member with member object
     */
    public void createNewMember (Member newMember) {
               
        em.persist(newMember);
    
    }
    
    /**
     * get an existed member by id 
     * @param memberId passing member id
     * @return member by id
     */
    public Member getExistedMemberById(int memberId) {
        
        
        Query readQuery = em.createQuery("SELECT targetMember FROM Member targetMember Where targetMember.id = :paraId");
        readQuery.setParameter("paraId", memberId);
        Member selectedMember = (Member) readQuery.getSingleResult();
        return selectedMember; 

    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateExistedMember(Member memberWithUpdatedFields) {
        em.merge(memberWithUpdatedFields);
    }
    
    /**
     * update an existed member
     * @param memberWithUpdatedFields member 
     */
    public void deletedExistedMemberById(int memberId) {

  
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Member> delete = cb.createCriteriaDelete(Member.class);
        Root<Member> member = delete.from(Member.class);
        
        delete.where(cb.equal(member.get(Member_.id), memberId));
        em.createQuery(delete).executeUpdate();
        
        
        
    }
    
    
    
}
