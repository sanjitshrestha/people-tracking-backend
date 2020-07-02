package com.sanjit.peopletracking.dto;

import com.sanjit.peopletracking.enumconstant.Status;

import java.io.Serializable;

public class RoleDTO implements Serializable {

    private Long id;

    private String name;

    private Status status;

    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
