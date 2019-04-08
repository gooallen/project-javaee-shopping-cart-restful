package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.PU_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.LineItem;

@Stateless
public class ShoppingCartBean implements ShoppingCart {
	
	// Done **
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;
       

    
    
    
    
    @Override
    public void addItem(String product, int quantity, double price) {
               
    }

    
    // Done **
	@Override
	public List<LineItem> getLineItem() {
	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<LineItem> cq = cb.createQuery(LineItem.class);
		Root<LineItem>rootEntry=cq.from(LineItem.class);
		CriteriaQuery<LineItem>all=cq.select(rootEntry);
		TypedQuery<LineItem>allQuery=em.createQuery(all);
		return allQuery.getResultList();
	}
	
	
	// Update LineItem is necessary?


	
    @Override
    public void checkout() {
        
    	
        
    }

    @Override
    public void removeItem() {
     
        
    }


}
