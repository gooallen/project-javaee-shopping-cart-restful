/**
 * File: Audit.java
 * Course: CST8277
 * @author: Mike Norman
 * @date: April 13th, 2019
 */
package com.algonquincollege.cst8277.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Audit {

    /**
     * initializing an object of date format
     */
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;

    /**
     * default construtor
     */
    public Audit() {
    }

    /**
     * get the created date
     * @return created date
     */
    @Column(name = "CREATED_DATE")
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    /**
     * set the created date
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * get the updated date
     * @return updated date
     */
    @Column(name = "UPDATED_DATE")
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    
    /**
     * set the updated date
     * @param updatedDate updated date
     */
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
        result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Audit other = (Audit) obj;
        if (createdDate == null) {
            if (other.createdDate != null)
                return false;
        } else if (!createdDate.equals(other.createdDate))
            return false;
        if (updatedDate == null) {
            if (other.updatedDate != null)
                return false;
        } else if (!updatedDate.equals(other.updatedDate))
            return false;
        return true;
    }

}