package com.algonquincollege.cst8277.models;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-03T18:19:39.607+0000")
@StaticMetamodel(Audit.class)
public class Audit_ {
	public static volatile SingularAttribute<Audit, LocalDateTime> createdDate;
	public static volatile SingularAttribute<Audit, LocalDateTime> updatedDate;
}
