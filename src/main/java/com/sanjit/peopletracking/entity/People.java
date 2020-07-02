package com.sanjit.peopletracking.entity;

import com.sanjit.peopletracking.abstractentity.AbstractEntity;
import com.sanjit.peopletracking.enumconstant.Status;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "people")
public class People extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "people_name")
    private String peopleName;

    @Column(name = "people_location")
    private String peopleLocation;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Email
    @Column(length = 100, unique = true)
    private String email;

    @Column(name = "status")
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
