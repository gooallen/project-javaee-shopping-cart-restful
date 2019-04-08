package com.algonquincollege.cst8277.models;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.ID_COLUMN_NAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PLATFORM_USER_INVERSEJOIN_COLUMN_NAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PLATFORM_USER_JOIN_COLUMN_NAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PLATFORM_USER_JOIN_TABLE_NAME;

import java.io.Serializable;
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
public class PlatformUser extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    public PlatformUser() {
        super();
    }

    protected String username;
    protected String pwHash;
    protected Set<PlatformRole> platformRoles = new HashSet<>();
    
    protected Member member;
    

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
    public void setPlatformRoles(Set<PlatformRole> platformRoles) {
        this.platformRoles = platformRoles;
    }
    
    
    @OneToOne(mappedBy= "platformUser")
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }

}