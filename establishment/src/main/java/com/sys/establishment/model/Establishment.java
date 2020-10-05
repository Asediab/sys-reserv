package com.sys.establishment.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "etablishment")
public class Establishment implements Serializable {

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

    @CollectionTable(name = "establishment_type", joinColumns = @JoinColumn(name = "establishment_id"))
    @Enumerated(EnumType.STRING)
    private TypeOfEstablishment typeOfEstablishment;

    @OneToMany(mappedBy = "establishment", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @CreatedDate
    private Date dateCreated;

    @LastModifiedDate
    private Date lastModifiedDate;

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
