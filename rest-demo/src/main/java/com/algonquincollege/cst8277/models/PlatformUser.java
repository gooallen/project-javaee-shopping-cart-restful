package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * User class used for (JSR-375) Java EE Security authorization/authentication
 */
@Entity
@Table(name="PLATFORM_USER")
public class PlatformUser extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    public PlatformUser() {
        super();
    }

    protected String username;
    protected String pwHash;
    protected List<PlatformRole> platformRoles;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwHash() {
        return pwHash;
    }
    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    @ManyToMany
    @JoinTable(name="PLATFORM_USER_ROLE",
      joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="ID"),
      inverseJoinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="ID"))
    public List<PlatformRole> getPlatformRoles() {
        return platformRoles;
    }
    public void setPlatformRoles(List<PlatformRole> platformRoles) {
        this.platformRoles = platformRoles;
    }

}