package com.algonquincollege.cst8277.ejb;

import java.util.List;
import javax.ejb.Remote;

import com.algonquincollege.cst8277.models.Cart;

// if EJB client is in different environment where EJB session bean need to be deployed
@Remote
public interface ShoppingCart {

    public void addItem(String product, int quantity, double price);
    public Cart getOrder();
    public void removeItem();
    public void checkout();
}
