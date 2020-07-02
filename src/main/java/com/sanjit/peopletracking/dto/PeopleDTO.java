package com.sanjit.peopletracking.dto;

import java.io.Serializable;

public class PeopleDTO implements Serializable {

    private Long id;

    private String peopleName;

    private String peopleLocation;

    private String phoneNumber;

    private String email;

    private String status;

    private String created;

    private String lastModified;

    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getPeopleLocation() {
        return peopleLocation;
    }

    public void setPeopleLocation(String peopleLocation) {
        this.peopleLocation = peopleLocation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
