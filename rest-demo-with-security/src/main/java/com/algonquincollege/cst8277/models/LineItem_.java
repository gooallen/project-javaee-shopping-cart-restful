package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-07T21:42:20.346+0000")
@StaticMetamodel(LineItem.class)
public class LineItem_ extends ModelBase_ {
	public static volatile SingularAttribute<LineItem, Product> product;
	public static volatile SingularAttribute<LineItem, Cart> cart;
	public static volatile SingularAttribute<LineItem, Double> subtotal;
	public static volatile SingularAttribute<LineItem, Integer> quantity;
}
