/**
 * File: BuildDefaultAdminuser.java
 * Course: CST8277
 * @author: Mike Norman
 * @date: April 13th, 2019
 */
package com.algonquincollege.cst8277.models;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.ID_COLUMN_NAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PLATFORM_USER_INVERSEJOIN_COLUMN_NAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PLATFORM_USER_JOIN_COLUMN_NAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PLATFORM_USER_JOIN_TABLE_NAME;

import java.io.Serializable;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.algonquincollege.cst8277.utils.RestDemoConstants;

/**
 * User class used for (JSR-375) Java EE Security authorization/authentication
 */
@Entity
@Table(name=RestDemoConstants.PLATFORM_USER_TABLE_NAME)
@NamedQueries(
    @NamedQuery(
        name=RestDemoConstants.FIND_PLATFORM_USER_BY_NAME_QUERYNAME,
        query=RestDemoConstants.FIND_PLATFORM_USER_BY_NAME_JPQL
    )
)
public class PlatformUser extends ModelBase implements Serializable, Principal {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * deafult constructor
     */
    public PlatformUser() {
        super();
    }

    /**
     * delcare username
     */
    protected String username;
    
    /**
     * declare password hash
     */
    protected String pwHash;
    
    /**
     * set of platformrole
     */
    protected Set<PlatformRole> platformRoles = new HashSet<>();
    
    /**
     * declare member
     */
    protected Member member;
    
    /**
     * username getter
     * @return username object
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * username setter
     * @param username username object
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * password hash getter
     * @return password hash object
     */
    public String getPwHash() {
        return pwHash;
    }
    
    /**
     * password hash setter
     * @param pwHash password hash object
     */
    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }
    
    /**
     * name getter
     * @return username object
     */
    @Override
    public String getName() {
        return username;
    }
    
    /**
     * platformroles getter
     * @return platformrole object
     */
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name=PLATFORM_USER_JOIN_TABLE_NAME,
      joinColumns=@JoinColumn(name=PLATFORM_USER_JOIN_COLUMN_NAME, referencedColumnName=ID_COLUMN_NAME),
      inverseJoinColumns=@JoinColumn(name=PLATFORM_USER_INVERSEJOIN_COLUMN_NAME, referencedColumnName=ID_COLUMN_NAME))
    public Set<PlatformRole> getPlatformRoles() {
        return platformRoles;
    }
    
    /**
     * platformroles setter
     * @param platformRoles platformrole object
     */
    public void setPlatformRoles(Set<PlatformRole> platformRoles) {
        this.platformRoles = platformRoles;
    }

    
    /**
     * member getter
     * @return member object
     */
    @OneToOne(mappedBy= "platformUser")
    public Member getMember() {
        return member;
    }
    
    /**
     * member setter
     * @param member member object
     */
    public void setMember(Member member) {
        this.member = member;
    }
    
    
    /**
     * add platformrole
     * @param role platformrole object
     */
    public void addPlatformRole(PlatformRole role) {
        platformRoles.add(role);
        role.getPlatformUsers().add(this);
    }

    /**
     * remove platformrole
     * @param role platform object
     */
    public void removePlatformRole(PlatformRole role) {
        platformRoles.remove(role);
        role.getPlatformUsers().remove(this);
    }

}