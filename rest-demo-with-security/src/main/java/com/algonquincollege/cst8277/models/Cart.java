package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * Order class
 */
@Entity
@EntityListeners({AuditListener.class})
@Table(name = "Cart")
public class Cart extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    private double total;
    private Collection<LineItem> lineItems;
    
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    @OneToMany(mappedBy = "cart")
    public Collection<LineItem> getLineItems() {
        return lineItems;
    }
    
    public void setLineItems(Collection<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
    
    public void calcPurchase(String product, int quantity, double price) {
        if(lineItems == null) lineItems = new ArrayList<>();
        LineItem item = new LineItem();
        item.setCart(this);
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setSubtotal(quantity * price);
        lineItems.add(item);
        
        total += quantity * price;
        
    }
    
    
}
