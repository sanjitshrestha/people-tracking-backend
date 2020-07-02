package com.sanjit.peopletracking.entity;

import com.sanjit.peopletracking.abstractentity.AbstractEntity;
import com.sanjit.peopletracking.enumconstant.Status;

import javax.persistence.*;

@Entity
@Table(name = "people_record")
public class PeopleRecord extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="camera_id", nullable = false)
    private Camera camera;

    @ManyToOne
    @JoinColumn(name = "people_id", nullable = false)
    private People people;

    @Column(name = "status")
    private Status status;

    @Column(name = "description")
    private String Description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
