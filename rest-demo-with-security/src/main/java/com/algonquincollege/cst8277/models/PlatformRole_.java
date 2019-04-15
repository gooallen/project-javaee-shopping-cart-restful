package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-15T00:16:57.922+0000")
@StaticMetamodel(PlatformRole.class)
public class PlatformRole_ extends ModelBase_ {
	public static volatile ListAttribute<PlatformRole, PlatformUser> platformUsers;
	public static volatile SingularAttribute<PlatformRole, String> roleName;
}
