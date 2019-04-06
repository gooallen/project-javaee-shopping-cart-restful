package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.PU_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algonquincollege.cst8277.models.Order;

@Stateless
public class ShoppingCartBean implements ShoppingCart {

    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;
    
    private Order order;
    

    @Override
    public void addItem(String product, int quantity, double price) {
        
        
    }

    @Override
    public Order getOrder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void checkout() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeItem() {
        // TODO Auto-generated method stub
        
    }

}
