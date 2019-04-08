package com.algonquincollege.cst8277.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * line item class
 */
@Entity
@EntityListeners({AuditListener.class})
@Table(name = "LINEITEM")
public class LineItem extends ModelBase implements Serializable {
    
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    private double subtotal;
    private int quantity;
    private Product product;

    private Cart cart;
    
    public double getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(double subTotal) {
        this.subtotal = subTotal;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    
    
    @ManyToOne
    @JoinColumn(name="cart_id")
    public Cart getCart() {
        return cart;
    }
    
    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
