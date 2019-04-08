/********************************************************************egg***m******a**************n************
 * File: Employee.java
 * Course materials (19W) CST 8277
 * @author Mike Norman
 * (Modified) @date 2019 03
 *
 * Copyright (c) 1998, 2009 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Original @authors dclarke, mbraeuer
 */
package com.algonquincollege.cst8277.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * Employee class
 */
@Entity
@EntityListeners({AuditListener.class})
public class Employee extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected String firstName;
    protected String lastName;
    protected double salary;

    public Employee() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lName) {
        this.lastName = lName;
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Employee)) {
            return false;
        }
        Employee other = (Employee)obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("Employee [id=")
            .append(id)
            .append(", firstName=")
            .append(firstName)
            .append(", lastName=")
            .append(lastName)
            .append(", salary=")
            .append(salary)
            .append(", version=")
            .append(version)
            .append(", created=")
            .append(audit.getCreatedDate())
            .append(", updated=")
            .append(audit.getUpdatedDate())
            .append("]")
        ;
        return builder.toString();
    }

}