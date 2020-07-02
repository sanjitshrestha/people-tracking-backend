package com.sanjit.peopletracking.entity;

import com.sanjit.peopletracking.abstractentity.AbstractEntity;
import com.sanjit.peopletracking.enumconstant.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
public class Location extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "city")
    private String city;

    @Column(name = "zip")
    private String zip;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "location")
    private List<Camera> cameraList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Camera> getCameraList() {
        return cameraList;
    }

    public void setCameraList(List<Camera> cameraList) {
        this.cameraList = cameraList;
    }
}
