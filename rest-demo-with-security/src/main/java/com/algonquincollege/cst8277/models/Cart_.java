package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-15T00:16:57.885+0000")
@StaticMetamodel(Cart.class)
public class Cart_ extends ModelBase_ {
	public static volatile ListAttribute<Cart, LineItem> lineItems;
	public static volatile SingularAttribute<Cart, Double> total;
	public static volatile SingularAttribute<Cart, Member> member;
}
