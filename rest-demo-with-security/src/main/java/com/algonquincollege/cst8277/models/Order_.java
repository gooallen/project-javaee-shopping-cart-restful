package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-05T01:26:22.120+0000")
@StaticMetamodel(Order.class)
public class Order_ extends ModelBase_ {
	public static volatile CollectionAttribute<Order, LineItem> lineItems;
	public static volatile SingularAttribute<Order, Double> total;
}
