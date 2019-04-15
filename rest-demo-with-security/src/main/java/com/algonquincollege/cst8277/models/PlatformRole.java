/**
 * File: BuildDefaultAdminuser.java
 * Course: CST8277
 * @author: Mike Norman
 * @date: April 13th, 2019
 */
package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Role class used for (JSR-375) Java EE Security authorization/authentication
 */
@Entity
@Table(name="PLATFORM_ROLE")
public class PlatformRole extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    
    /**
     * default constructor
     */
    public PlatformRole() {
        super();
    }
    
    /**
     * declare roleName
     */
    protected String roleName;
    
    /**
     * declare list of PlatformUser object
     */
    protected List<PlatformUser> platformUsers;
    

    /**
     * rolename getter
     * @return rolename object
     */
    public String getRoleName() {
        return roleName;
    }
    
    /**
     * rolename setter
     * @param rolename object
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * platformusers getter
     * @return list of platformusers
     */
    @ManyToMany(mappedBy="platformRoles")
    public List<PlatformUser> getPlatformUsers() {
        return platformUsers;
    }
    
    /**
     * platformuser setter
     * @param platformUsers playformUsers object
     */
    public void setPlatformUsers(List<PlatformUser> platformUsers) {
        this.platformUsers = platformUsers;
    }
    
    /**
     * add platformuser 
     * @param user platformuser object
     */
    public void addPlatformUser(PlatformUser user) {
        
        platformUsers = new ArrayList<>();
        
        platformUsers.add(user);
        user.getPlatformRoles().add(this);
    }

    /**
     * remove platformuser 
     * @param user platformuser object
     */
    public void removePlatformUser(PlatformUser user) {
        
        platformUsers = new ArrayList<>();
        
        platformUsers.remove(user);
        user.getPlatformRoles().remove(this);
    }

}