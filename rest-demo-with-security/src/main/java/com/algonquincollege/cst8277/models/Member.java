package com.algonquincollege.cst8277.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Product class
 */
@Entity
@EntityListeners({AuditListener.class})
@Table(name = "MEMBER")
public class Member extends ModelBase implements Serializable {
    
    private String memberFirstName;
    private String memberLastName;
    private String memberEmail;
    private String memberPhone;
    
    private Cart cart;
    
    private PlatformUser platformUser;
    private PlatformRole platformRole;

    public String getMemberFirstName() {
        return memberFirstName;
    }

    public void setMemberFirstName(String memberFirstName) {
        this.memberFirstName = memberFirstName;
    }

    public String getMemberLastName() {
        return memberLastName;
    }

    public void setMemberLastName(String memberLastName) {
        this.memberLastName = memberLastName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    @OneToOne()
    @JoinColumn(name="CART_ID", referencedColumnName="id", unique=true)
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @OneToOne()
    @JoinColumn(name= "USER_ID", referencedColumnName="id", unique=true)
    public PlatformUser getPlatformUser() {
        return platformUser;
    }
    
    
    public void setPlatformUser(PlatformUser platformUser) {
        this.platformUser = platformUser;
    }

    
    @OneToOne()
    @JoinColumn(name= "ROLE_ID", referencedColumnName="id", unique=true)
    public PlatformRole getPlatformRole() {
        return platformRole;
    }

    public void setPlatformRole(PlatformRole platformRole) {
        this.platformRole = platformRole;
    }
}
