package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Order class
 */
@Entity
@EntityListeners({AuditListener.class})
@Table(name="CART")
public class Cart extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    private double total;
    private List<LineItem> lineItems = new ArrayList<>();
    
    private Member member;
    

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    @OneToMany(mappedBy = "cart")
    public List<LineItem> getLineItems() {
        return lineItems;
    }
    
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
    
    @OneToOne(mappedBy="cart")
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    
    
}
