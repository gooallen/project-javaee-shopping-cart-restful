package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-07T00:00:46.821+0000")
@StaticMetamodel(LineItem.class)
public class LineItem_ extends ModelBase_ {
	public static volatile SingularAttribute<LineItem, Double> subtotal;
	public static volatile SingularAttribute<LineItem, Integer> quantity;
	public static volatile SingularAttribute<LineItem, String> product;
	public static volatile SingularAttribute<LineItem, Cart> cart;
}
