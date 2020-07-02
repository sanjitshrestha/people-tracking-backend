package com.sanjit.peopletracking.entity;

import com.sanjit.peopletracking.abstractentity.AbstractEntity;
import com.sanjit.peopletracking.enumconstant.Status;

import javax.persistence.*;

@Entity
@Table(name = "camera")
public class Camera extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "camera_name")
    private String cameraName;

    @Column(name = "camera_code")
    private String cameraCode;

    @Column(name = "direction")
    private String direction;

    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getCameraCode() {
        return cameraCode;
    }

    public void setCameraCode(String cameraCode) {
        this.cameraCode = cameraCode;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
