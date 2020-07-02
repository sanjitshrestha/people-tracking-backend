package com.sanjit.peopletracking.abstractentity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @Version
    @Column
    private int version;

    public AbstractEntity() {
        setCreated(new Date());
    }

    @PreUpdate
    public void preUpdate() {
        lastModified = new Date();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
