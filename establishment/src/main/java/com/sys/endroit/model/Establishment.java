package com.sys.endroit.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "etablishment")
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 2000)
    private String description;

    @NotBlank
    @Column(nullable = false, length = 500)
    private String address;

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "time_table_id", referencedColumnName = "id")
    private TimeTable timeTable;

    @NotBlank
    @Column(nullable = false)
    private String picture;

    @Positive
    private int clients_limit;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeOfEstablishment.class)
    @JoinColumn(name = "type_of_establishment_id", nullable = false)
    private TypeOfEstablishment typeOfEstablishment;

    @OneToMany(mappedBy = "establishment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;

    public Establishment() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getClients_limit() {
        return clients_limit;
    }

    public void setClients_limit(int clients_limit) {
        this.clients_limit = clients_limit;
    }

    public TypeOfEstablishment getTypeOfEstablishment() {
        return typeOfEstablishment;
    }

    public void setTypeOfEstablishment(TypeOfEstablishment typeOfEstablishment) {
        this.typeOfEstablishment = typeOfEstablishment;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
