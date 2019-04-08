package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Product class
 */
@Entity
@EntityListeners({AuditListener.class})
@Table(name = "PRODUCT")
public class Product extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    private String productName;
    private String productDescription;
    private double productPrice;
    
    private List<LineItem> lineItems = new ArrayList<>();
    //private LineItem lineItem = new LineItem();
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getProductDescription() {
        return productDescription;
    }
    
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
    public double getProductPrice() {
        return productPrice;
    }
    
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    
    @OneToMany(mappedBy = "product")
    public List<LineItem> getLineItem() {
        return lineItems;
    }
    
    public void setLineItem(List<LineItem> lineitems) {
        this.lineItems = lineItems;
    }
    
}
