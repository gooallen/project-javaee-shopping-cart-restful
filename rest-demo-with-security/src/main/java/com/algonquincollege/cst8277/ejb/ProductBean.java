/**
 * File: ProductBean.java
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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.algonquincollege.cst8277.models.Member;
import com.algonquincollege.cst8277.models.Member_;
import com.algonquincollege.cst8277.models.Product;
import com.algonquincollege.cst8277.models.Product_;



@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 20)
public class ProductBean {
    
    /**
     * default constructor
     */
    public ProductBean(){
        //default constructor
    }
    
    /**
     * initializing an EntitiyManager
     */
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;
    
    /**
     * get a list of products
     * @return the list of products
     */
    public List<Product> getProductList() {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);
        cq.select(product);
        TypedQuery<Product> typedQuery = em.createQuery(cq);
        List<Product> result = typedQuery.getResultList();
        
        return result;
    }
    
    /**
     * create a new product 
     * @param newProduct new product object
     */
    public void createNewProduct(Product newProduct) {
             

        em.persist(newProduct);
    }
    
    /**
     * get an existed product by id
     * @param productId passing the product id
     * @return the first product from the list
     */
    public Product getExistedProductById(int productId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);
        Predicate condition = cb.equal(product.get(Product_.id), productId);
        
        cq.select(product).where(condition);
        
        TypedQuery<Product> typedQuery = em.createQuery(cq);
        List<Product> result = typedQuery.getResultList();
        
        return result.get(0);
        
    }
    
    /**
     * update an existed product
     * @param productWithupdatedFields
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateExistedProduct(Product productWithupdatedFields) {
        em.merge(productWithupdatedFields);
    }
    
    
    /**
     * delete an existed product by id
     * @param productId passing the product id
     */
    public void deletedExistedProductById(int productId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Product> delete = cb.createCriteriaDelete(Product.class);
        Root<Product> product = delete.from(Product.class);
        
        delete.where(cb.equal(product.get(Product_.id), productId));
        em.createQuery(delete).executeUpdate();
        


    }
}

