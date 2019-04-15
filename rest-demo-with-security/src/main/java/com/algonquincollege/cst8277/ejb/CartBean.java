/**
 * File: CartBean.java
 * Course: CST8277
 * @author: Byeongyun Goo (#040888224), Zeyang Hu (#040885680), Sohaila Binte Ridwan (#040847430)
 * @date: April 13th, 2019
 */
package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.PU_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.Cart_;
import com.algonquincollege.cst8277.models.LineItem;
import com.algonquincollege.cst8277.models.LineItem_;
import com.algonquincollege.cst8277.models.Member;
import com.algonquincollege.cst8277.models.Product;

@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 20)
public class CartBean {
    
    /**
     * initializing the entitiyManager
     */
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;
    
    /**
    * default constructor
    */
    public CartBean() {
        //default constructor
    }
    
    /**
     * get a cart contents (R)
     * @param cartId to pass cart id
     */
    public Cart getCartContent(int cartId) {
        
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<LineItem> cq = cb.createQuery(LineItem.class);
//        Root<LineItem> items = cq.from(LineItem.class);
//        cq.select(items);
//        TypedQuery<LineItem> typedQuery = em.createQuery(cq);
//        List<LineItem> result = typedQuery.getResultList();
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cart> cq = cb.createQuery(Cart.class);
        Root<Cart> cart = cq.from(Cart.class);
        
        Predicate condition = cb.equal(cart.get(Cart_.id), cartId);
        cq.select(cart).where(condition);
        TypedQuery<Cart> typedQuery = em.createQuery(cq);
        Cart result = typedQuery.getSingleResult();
          
        return result;
    }
    
    /**
     * get all carts (R)
     * @return list of carts
     */
    public List<Cart> getAllCarts() {
       
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cart> cq = cb.createQuery(Cart.class);
        Root<Cart> cart = cq.from(Cart.class);
        cq.select(cart);
        TypedQuery<Cart> typedQuery = em.createQuery(cq);
        List<Cart> result = typedQuery.getResultList();
        
        return result;
    }
    
    /**
     * delete cart by cart id (D)
     * @param cartId to pass cart id
     */
    public void deleteCart(int cartId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Cart> delete = cb.createCriteriaDelete(Cart.class);
        Root<Cart> cart = delete.from(Cart.class);
        
        delete.where(cb.equal(cart.get(Cart_.id), cartId));
        em.createQuery(delete).executeUpdate();
        
    }
    
    /**
     * create cart with member info (C)
     * @param member use member info
     */
    public void createCart (Member member) {
        
        Cart cart = new Cart();
        cart.setMember(member);
        cart.setLineItems(null);
        cart.setTotal(0);
        em.persist(cart);
    }
    
    /**
     * update cart information (U)
     * @param cartWithUpdatedField keep tracking which field is updating
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateExistedCart(Cart cartWithUpdatedField) {
        
        //cartWithUpdatedField.setTotal(this.getTotalAmount(cartWithUpdatedField));
        
        
        em.merge(cartWithUpdatedField);
    }
    
    /**
     * get total amount from cart (R)
     * @param cart to use cart info
     * @return total price
     */
    public double getTotalAmount (Cart cart) {
        double total = 0;
        
        Query selectQuery = em.createQuery("SELECT lineItem FROM LineItem lineItem WHERE lineItem.cart_id = :paraCartID");
        
        selectQuery.setParameter("paraCartID", cart.getId());
        
        List<LineItem> lineItems = selectQuery.getResultList();
              
        for(LineItem lineItem : lineItems) {
            total += lineItem.getSubtotal();
        }
        
        return total;
        
    }
    
}
