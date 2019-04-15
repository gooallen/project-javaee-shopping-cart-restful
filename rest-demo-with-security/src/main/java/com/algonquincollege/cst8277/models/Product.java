/**
 * File: Product.java
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
    
    /**
     * declare productcode
     */
    private String productCode;
    
    /**
     * declare productname
     */
    private String productName;
    
    /**
     * declare productdescription
     */
    private String productDescription;
    
    /**
     * declare productprice
     */
    private double productPrice;
    
    /**
     * initializing list of lineitem
     */
    private List<LineItem> lineItems = new ArrayList<>();
    //private LineItem lineItem = new LineItem();
    
    
    /**
     * productname getter
     * @return productname object
     */
    public String getProductName() {
        return productName;
    }
    
    
    /**
     * productname setter
     * @param productname object
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    /**
     * productdescription getter
     * @return productdescription object
     */
    public String getProductDescription() {
        return productDescription;
    }
    
    /**
     * productdescription setter
     * @param productdescription object
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
    /**
     * productprice getter
     * @return productprice object
     */
    public double getProductPrice() {
        return productPrice;
    }
    
    /**
     * productprice setter
     * @param productprice object
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    
    /**
     * productcode getter
     * @return productcode object
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * product setter
     * @param productCode productcode object
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    /**
     * lineitem getter
     * @return List lineitem objects
     */
    @OneToMany(mappedBy = "product")
    public List<LineItem> getLineItem() {
        return lineItems;
    }
    /**
     * lineitem setter
     * @param lineitems lineitem object
     */
    public void setLineItem(List<LineItem> lineitems) {
        this.lineItems = lineItems;
    }

    
    
}
