package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-15T00:16:57.947+0000")
@StaticMetamodel(Product.class)
public class Product_ extends ModelBase_ {
	public static volatile ListAttribute<Product, LineItem> lineItem;
	public static volatile SingularAttribute<Product, String> productName;
	public static volatile SingularAttribute<Product, String> productDescription;
	public static volatile SingularAttribute<Product, Double> productPrice;
	public static volatile SingularAttribute<Product, String> productCode;
}
