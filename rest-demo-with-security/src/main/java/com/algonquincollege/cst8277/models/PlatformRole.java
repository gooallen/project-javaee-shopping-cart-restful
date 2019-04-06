package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Role class used for (JSR-375) Java EE Security authorization/authentication
 */
@Entity
@Table(name="PLATFORM_ROLE")
public class PlatformRole extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    public PlatformRole() {
        super();
    }

    protected String roleName;
    protected List<PlatformUser> platformUsers;

    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ManyToMany(mappedBy="platformRoles")
    public List<PlatformUser> getPlatformUsers() {
        return platformUsers;
    }
    public void setPlatformUsers(List<PlatformUser> platformUsers) {
        this.platformUsers = platformUsers;
    }


}