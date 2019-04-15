package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-15T00:16:57.934+0000")
@StaticMetamodel(PlatformUser.class)
public class PlatformUser_ extends ModelBase_ {
	public static volatile SetAttribute<PlatformUser, PlatformRole> platformRoles;
	public static volatile SingularAttribute<PlatformUser, Member> member;
	public static volatile SingularAttribute<PlatformUser, String> username;
	public static volatile SingularAttribute<PlatformUser, String> pwHash;
}
