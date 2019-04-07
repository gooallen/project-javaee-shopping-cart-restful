package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-06T23:59:30.257+0000")
@StaticMetamodel(Cart.class)
public class Cart_ extends ModelBase_ {
	public static volatile CollectionAttribute<Cart, LineItem> lineItems;
	public static volatile SingularAttribute<Cart, Double> total;
}
