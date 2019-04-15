/**
 * File: BuildDefaultAdminuser.java
 * Course: CST8277
 * @author: Mike Norman
 * @date: April 13th, 2019
 */
package com.algonquincollege.cst8277.models;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditListener {

    /**
     * created date setter
     * @param auditable audit object
     */
    @PrePersist
    public void setCreatedDate(Auditable auditable) {
        Audit audit = auditable.getAudit();
        audit.setCreatedDate(LocalDateTime.now());
    }

    /**
     * updated date setter 
     * @param auditable audit object
     */
    @PreUpdate
    public void setUpdatedDate(Auditable auditable) {
        Audit audit = auditable.getAudit();
        audit.setUpdatedDate(LocalDateTime.now());
    }
}