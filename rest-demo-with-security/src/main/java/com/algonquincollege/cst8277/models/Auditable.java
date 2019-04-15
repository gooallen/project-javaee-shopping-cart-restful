package com.algonquincollege.cst8277.models;

public interface Auditable {
    /**
     * Audit getter
     */
    public Audit getAudit();
    
    /**
     * Audit setter
     * @param audit audit object
     */
    public void setAudit(Audit audit);
}
