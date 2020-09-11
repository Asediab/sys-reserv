package com.sys.reservation.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long establishmentId;

    @NotBlank
    @Column(nullable = false)
    private String establishmentName;

    @NotNull
    @Column(nullable = false)
    private Long userId;

    @NotBlank
    @Column(nullable = false)
    private String userFirstName;

    @NotNull
    @Column(nullable = false, unique = true)
    private String validateNumber;

    @NotNull
    @Column(columnDefinition = "false")
    private boolean valid;

    @NotNull
    @Column(nullable = false)
    private int beginning;

    @NotNull
    @Column(nullable = false)
    private int numberOfHours;

    @CreatedDate
    private Date dateCreated;

    @LastModifiedDate
    private Date lastModifiedDate;

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(Long establishmentId) {
        this.establishmentId = establishmentId;
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getValidateNumber() {
        return validateNumber;
    }

    public void setValidateNumber(String validateNumber) {
        this.validateNumber = validateNumber;
    }

    public int getBeginning() {
        return beginning;
    }

    public void setBeginning(int beginning) {
        this.beginning = beginning;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
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

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
