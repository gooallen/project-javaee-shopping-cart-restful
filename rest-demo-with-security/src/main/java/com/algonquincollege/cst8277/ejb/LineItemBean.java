/**
 * File: LineItemBean.java
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
public class LineItemBean {

    /**
     * initalizing the EntityManager
     */
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;
    
    /**
     * deafult constructor
     */
    public LineItemBean() {
        
    }
    
    /**
     * get a list of items (C)
     * @return the list of items
     */
    public List<LineItem> getLineItemList(int cartId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LineItem> cq = cb.createQuery(LineItem.class);

        Root<Cart> cart = cq.from(Cart.class);
        Join<Cart, LineItem> lineItems = cart.join("lineItems");
        
        Predicate condition = cb.equal(cart.get(Cart_.id),cartId);
        cq.select(lineItems).where(condition);
        
        TypedQuery<LineItem> typedQuery = em.createQuery(cq);
        List<LineItem> result = typedQuery.getResultList();
        return result;
        
    }
    
    
    /**
     * create a new lineitem (C)
     * @param cartId passing cart id
     * @param productId passing product id
     * @param quantity passing quantity
     */
    public void createNewLineItem(int cartId, int productId, int quantity) {
        
    
        LineItem lineItem = new LineItem();
          
          Query selectCartQuery = em.createQuery("SELECT cart FROM Cart cart WHERE cart.id = :paraCartId");
          selectCartQuery.setParameter("paraCartId", cartId);
          Cart targetCart = (Cart) selectCartQuery.getSingleResult();
          
          lineItem.setCart(targetCart);
          
          Query selectProductQuery = em.createQuery("SELECT product FROM Product product WHERE product.id = :paraProductId");
          selectProductQuery.setParameter("paraProductId", productId);
          Product targetProduct = (Product)selectProductQuery.getSingleResult();
          
          
          
          lineItem.setProduct(targetProduct);
          lineItem.setQuantity(0);
    
          int newQuantity = lineItem.getQuantity() + quantity;
          
          if(newQuantity >=0) {
              double subTotal = targetProduct.getProductPrice() * newQuantity;
              lineItem.setSubtotal(subTotal);
              
              em.persist(lineItem);

          }
        }
    
    
    /**
     * delete lineitem (D)
     * @param cartId passing cart id
     * @param productId passing product id
     */
    public void deleteLineItem(int lineItemId) {
        LineItem lineItem = em.find(LineItem.class, lineItemId);
        
        if(lineItem != null) {
            em.remove(lineItem);
        }
    }
}
