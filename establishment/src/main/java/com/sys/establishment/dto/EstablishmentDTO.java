package com.sys.establishment.dto;

import com.sys.establishment.model.TypeOfEstablishment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class EstablishmentDTO implements Serializable {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String address;

    private TimeTableDTO timeTable;

    @NotBlank
    private String picture;

    @Positive
    private int clients_limit;

    private TypeOfEstablishment typeOfEstablishment;

    private List<CommentDTO> comments;

    private LocalDateTime dateCreated;

    private LocalDateTime lastModifiedDate;

    public EstablishmentDTO() {
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public TimeTableDTO getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTableDTO timeTable) {
        this.timeTable = timeTable;
    }

    public TypeOfEstablishment getTypeOfEstablishment() {
        return typeOfEstablishment;
    }

    public void setTypeOfEstablishment(TypeOfEstablishment typeOfEstablishment) {
        this.typeOfEstablishment = typeOfEstablishment;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
