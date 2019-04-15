/********************************************************************egg***m******a**************n************
 * File: ModelBase.java
 * Course materials (19W) CST 8277
 * @author Mike Norman
 * @date 2019 03
 *
 */
package com.algonquincollege.cst8277.models;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class ModelBase implements Auditable {

    /**
     * declare id
     */
    protected int id;
    
    /**
     * declare version
     */
    protected int version;
    
    /**
     * initializing audit object
     */
    protected Audit audit = new Audit();

    /**
     * default constructor
     */
    public ModelBase() {
        super();
        this.audit.setCreatedDate(LocalDateTime.now());
        // created_date and updated_date have the same value at the very beginning of object's lifecycle
        this.audit.setUpdatedDate(this.audit.getCreatedDate());
    }

    /**
     * id getter
     * @return id object
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    
    /**
     * id setter
     * @param id id object
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * version getter
     * @return version object
     */
    @Version
    public int getVersion() {
        return version;
    }
    
    /**
     * version setter
     * @param version version object
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * audit getter
     * @return audit object
     */
    public Audit getAudit() {
        return audit;
    }
    
    /**
     * audit setter
     * @param audit audit object
     */
    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((audit == null) ? 0 : audit.hashCode());
        result = prime * result + id;
        result = prime * result + version;
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
        ModelBase other = (ModelBase) obj;
        if (audit == null) {
            if (other.audit != null)
                return false;
        } else if (!audit.equals(other.audit))
            return false;
        if (id != other.id)
            return false;
        if (version != other.version)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ModelBase [id=" + id + ", version=" + version + ", audit=" + audit + "]";
    }
}