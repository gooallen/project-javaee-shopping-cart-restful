/**
 * File: Member.java
 * Course: CST8277
 * @author: Byeongyun Goo (#040888224), Zeyang Hu (#040885680), Sohaila Binte Ridwan (#040847430)
 * @date: April 13th, 2019
 */
package com.algonquincollege.cst8277.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Member class
 */
@Entity
@EntityListeners({AuditListener.class})
@Table(name = "MEMBER")
public class Member extends ModelBase implements Serializable {
    
    /**
     * delcaring the member of first name object
     */
    private String memberFirstName;
    
    /**
     * declaring the member of last name object
     */
    private String memberLastName;
    
    /**
     * delcaring the member of email object
     */
    private String memberEmail;
    
    /**
     * declaring the member of phoen object
     */
    private String memberPhone;
    
    /**
     * declaring the member of cart object
     */
    private Cart cart;
    
    public Member() {
        super();
    }
    
    /**
     * declaring PlatformUser object
     */
    private PlatformUser platformUser;

    /**
     * memberfirstname getter
     * @return memberfirstname object
     */
    public String getMemberFirstName() {
        return memberFirstName;
    }

    /**
     * memberfisrtname setter
     * @param memberFirstName memberfirstname object
     */
    public void setMemberFirstName(String memberFirstName) {
        this.memberFirstName = memberFirstName;
    }

    /**
     * memberlastname getter
     * @return memberlastname object
     */
    public String getMemberLastName() {
        return memberLastName;
    }

    /**
     * memberlastname setter
     * @param memberLastName memberlastname ojbect
     */
    public void setMemberLastName(String memberLastName) {
        this.memberLastName = memberLastName;
    }

    /**
     * memberemail getter
     * @return memberemail object
     */
    public String getMemberEmail() {
        return memberEmail;
    }

    /**
     * memberemail setter
     * @param memberEmail memberemail object
     */
    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    /**
     * memberphone getter
     * @return memberphone object
     */
    public String getMemberPhone() {
        return memberPhone;
    }

    /**
     * memberphone setter
     * @param memberPhone memberphone object
     */
    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    /**
     * cart getter
     * @return cart object
     */
    @OneToOne(mappedBy ="member")
    public Cart getCart() {
        return cart;
    }

    /**
     * cart setter
     * @param cart cart object
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * platformuser getter
     * @return platformuser object
     */
//    @OneToOne()
//    @JoinColumn(name= "PLATFORMUSER_ID", referencedColumnName="id", unique=true)
    public PlatformUser getPlatformUser() {
        return platformUser;
    }
    
    /**
     * platformuser setter
     * @param platformUser platformUser object
     */
    public void setPlatformUser(PlatformUser platformUser) {
        this.platformUser = platformUser;
    }

    @Override
    public String toString() {
        return "Member [memberFirstName=" + memberFirstName + ", memberLastName=" + memberLastName + ", memberEmail="
                + memberEmail + ", memberPhone=" + memberPhone + ", cart=" + cart + ", platformUser=" + platformUser
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cart == null) ? 0 : cart.hashCode());
        result = prime * result + ((memberEmail == null) ? 0 : memberEmail.hashCode());
        result = prime * result + ((memberFirstName == null) ? 0 : memberFirstName.hashCode());
        result = prime * result + ((memberLastName == null) ? 0 : memberLastName.hashCode());
        result = prime * result + ((memberPhone == null) ? 0 : memberPhone.hashCode());
        result = prime * result + ((platformUser == null) ? 0 : platformUser.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Member other = (Member) obj;
        if (cart == null) {
            if (other.cart != null)
                return false;
        } else if (!cart.equals(other.cart))
            return false;
        if (memberEmail == null) {
            if (other.memberEmail != null)
                return false;
        } else if (!memberEmail.equals(other.memberEmail))
            return false;
        if (memberFirstName == null) {
            if (other.memberFirstName != null)
                return false;
        } else if (!memberFirstName.equals(other.memberFirstName))
            return false;
        if (memberLastName == null) {
            if (other.memberLastName != null)
                return false;
        } else if (!memberLastName.equals(other.memberLastName))
            return false;
        if (memberPhone == null) {
            if (other.memberPhone != null)
                return false;
        } else if (!memberPhone.equals(other.memberPhone))
            return false;
        if (platformUser == null) {
            if (other.platformUser != null)
                return false;
        } else if (!platformUser.equals(other.platformUser))
            return false;
        return true;
    }
    


}
