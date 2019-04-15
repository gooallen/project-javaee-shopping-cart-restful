package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-15T00:16:57.912+0000")
@StaticMetamodel(Member.class)
public class Member_ extends ModelBase_ {
	public static volatile SingularAttribute<Member, Cart> cart;
	public static volatile SingularAttribute<Member, String> memberFirstName;
	public static volatile SingularAttribute<Member, String> memberLastName;
	public static volatile SingularAttribute<Member, String> memberEmail;
	public static volatile SingularAttribute<Member, String> memberPhone;
	public static volatile SingularAttribute<Member, PlatformUser> platformUser;
}
