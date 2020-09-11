package com.sys.reservation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


public class ReservationDTO implements Serializable {

    private Long id;

    @NotNull
    private Long establishmentId;

    @NotBlank
    private String establishmentName;

    @NotNull
    private Long userId;

    @NotBlank
    private String userFirstName;

    private String validateNumber;

    private boolean valid;

    @NotNull
    private int beginning;

    @NotNull
    private int numberOfHours;

    private Date dateCreated;

    private Date lastModifiedDate;

    public ReservationDTO() {
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
