/**
 * File: Cart.java
 * Course: CST8277
 * @author: Byeongyun Goo (#040888224), Zeyang Hu (#040885680), Sohaila Binte Ridwan (#040847430)
 * @date: April 13th, 2019
 */
package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Cart class
 */
@Entity
@EntityListeners({AuditListener.class})
@Table(name="CART")
public class Cart extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /**
     * declare total
     */
    private double total;
    
    /**
     * initializing a list of LineItem
     */
    private List<LineItem> lineItems = new ArrayList<>();
    
    /**
     * delcare member
     */
    private Member member;
    
    public Cart() {
        super();
    }
    
    /**
     * get total
     * @return total 
     */
    public double getTotal() {
        return total;
    }

    /**
     * set total
     * @param total object
     */
    public void setTotal(double total) {
        this.total = total;
    }
    
    /**
     * lineitem getter
     * @return lineitem object
     */
    @OneToMany(mappedBy = "cart")
    public List<LineItem> getLineItems() {
        return lineItems;
    }
    
    /**
     * lineitem setter
     * @param lineItems passing lineitem object
     */
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
    

    /**
     * member getter
     * @return member
     */
//    @OneToOne() 
//    @JoinColumn(name="member_id", referencedColumnName="id", unique=true)
    public Member getMember() {
      return member;
    }
    
    /**
     * member setter
     * @param member passing member objeft
     */
    public void setMember(Member member) {
      this.member = member;
    }
    
}
