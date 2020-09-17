package com.sys.establishment.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "time_table")
public class TimeTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "timeTable")
    private Establishment establishment;

    @NotNull
    @Column(nullable = false)
    private Date mondayAMStart;
    @NotNull
    @Column(nullable = false)
    private Date mondayAMEnd;

    @NotNull
    @Column(nullable = false)
    private Date mondayPMStart;
    @NotNull
    @Column(nullable = false)
    private Date mondayPMEnd;

    @NotNull
    @Column(nullable = false)
    private Date tuesdayAMStart;
    @NotNull
    @Column(nullable = false)
    private Date tuesdayAMEnd;

    @NotNull
    @Column(nullable = false)
    private Date tuesdayPMStart;
    @NotNull
    @Column(nullable = false)
    private Date tuesdayPMEnd;

    @NotNull
    @Column(nullable = false)
    private Date wednesdayAMStart;
    @NotNull
    @Column(nullable = false)
    private Date wednesdayAMEnd;

    @NotNull
    @Column(nullable = false)
    private Date wednesdayPMStart;
    @NotNull
    @Column(nullable = false)
    private Date wednesdayPMEnd;

    @NotNull
    @Column(nullable = false)
    private Date thursdayAMStart;
    @NotNull
    @Column(nullable = false)
    private Date thursdayAMEnd;

    @NotNull
    @Column(nullable = false)
    private Date thursdayPMStart;
    @NotNull
    @Column(nullable = false)
    private Date thursdayPMEnd;

    @NotNull
    @Column(nullable = false)
    private Date fridayAMStart;
    @NotNull
    @Column(nullable = false)
    private Date fridayAMEnd;

    @NotNull
    @Column(nullable = false)
    private Date fridayPMStart;
    @NotNull
    @Column(nullable = false)
    private Date fridayPMEnd;

    @NotNull
    @Column(nullable = false)
    private Date saturdayAMStart;
    @NotNull
    @Column(nullable = false)
    private Date saturdayAMEnd;

    @NotNull
    @Column(nullable = false)
    private Date saturdayPMStart;
    @NotNull
    @Column(nullable = false)
    private Date saturdayPMEnd;

    @NotNull
    @Column(nullable = false)
    private Date sundayAMStart;
    @NotNull
    @Column(nullable = false)
    private Date sundayAMEnd;

    @NotNull
    @Column(nullable = false)
    private Date sundayPMStart;
    @NotNull
    @Column(nullable = false)
    private Date sundayPMEnd;

    public TimeTable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public Date getMondayAMStart() {
        return mondayAMStart;
    }

    public void setMondayAMStart(Date mondayAMStart) {
        this.mondayAMStart = mondayAMStart;
    }

    public Date getMondayAMEnd() {
        return mondayAMEnd;
    }

    public void setMondayAMEnd(Date mondayAMEnd) {
        this.mondayAMEnd = mondayAMEnd;
    }

    public Date getMondayPMStart() {
        return mondayPMStart;
    }

    public void setMondayPMStart(Date mondayPMStart) {
        this.mondayPMStart = mondayPMStart;
    }

    public Date getMondayPMEnd() {
        return mondayPMEnd;
    }

    public void setMondayPMEnd(Date mondayPMEnd) {
        this.mondayPMEnd = mondayPMEnd;
    }

    public Date getTuesdayAMStart() {
        return tuesdayAMStart;
    }

    public void setTuesdayAMStart(Date tuesdayAMStart) {
        this.tuesdayAMStart = tuesdayAMStart;
    }

    public Date getTuesdayAMEnd() {
        return tuesdayAMEnd;
    }

    public void setTuesdayAMEnd(Date tuesdayAMEnd) {
        this.tuesdayAMEnd = tuesdayAMEnd;
    }

    public Date getTuesdayPMStart() {
        return tuesdayPMStart;
    }

    public void setTuesdayPMStart(Date tuesdayPMStart) {
        this.tuesdayPMStart = tuesdayPMStart;
    }

    public Date getTuesdayPMEnd() {
        return tuesdayPMEnd;
    }

    public void setTuesdayPMEnd(Date tuesdayPMEnd) {
        this.tuesdayPMEnd = tuesdayPMEnd;
    }

    public Date getWednesdayAMStart() {
        return wednesdayAMStart;
    }

    public void setWednesdayAMStart(Date wednesdayAMStart) {
        this.wednesdayAMStart = wednesdayAMStart;
    }

    public Date getWednesdayAMEnd() {
        return wednesdayAMEnd;
    }

    public void setWednesdayAMEnd(Date wednesdayAMEnd) {
        this.wednesdayAMEnd = wednesdayAMEnd;
    }

    public Date getWednesdayPMStart() {
        return wednesdayPMStart;
    }

    public void setWednesdayPMStart(Date wednesdayPMStart) {
        this.wednesdayPMStart = wednesdayPMStart;
    }

    public Date getWednesdayPMEnd() {
        return wednesdayPMEnd;
    }

    public void setWednesdayPMEnd(Date wednesdayPMEnd) {
        this.wednesdayPMEnd = wednesdayPMEnd;
    }

    public Date getThursdayAMStart() {
        return thursdayAMStart;
    }

    public void setThursdayAMStart(Date thursdayAMStart) {
        this.thursdayAMStart = thursdayAMStart;
    }

    public Date getThursdayAMEnd() {
        return thursdayAMEnd;
    }

    public void setThursdayAMEnd(Date thursdayAMEnd) {
        this.thursdayAMEnd = thursdayAMEnd;
    }

    public Date getThursdayPMStart() {
        return thursdayPMStart;
    }

    public void setThursdayPMStart(Date thursdayPMStart) {
        this.thursdayPMStart = thursdayPMStart;
    }

    public Date getThursdayPMEnd() {
        return thursdayPMEnd;
    }

    public void setThursdayPMEnd(Date thursdayPMEnd) {
        this.thursdayPMEnd = thursdayPMEnd;
    }

    public Date getFridayAMStart() {
        return fridayAMStart;
    }

    public void setFridayAMStart(Date fridayAMStart) {
        this.fridayAMStart = fridayAMStart;
    }

    public Date getFridayAMEnd() {
        return fridayAMEnd;
    }

    public void setFridayAMEnd(Date fridayAMEnd) {
        this.fridayAMEnd = fridayAMEnd;
    }

    public Date getFridayPMStart() {
        return fridayPMStart;
    }

    public void setFridayPMStart(Date fridayPMStart) {
        this.fridayPMStart = fridayPMStart;
    }

    public Date getFridayPMEnd() {
        return fridayPMEnd;
    }

    public void setFridayPMEnd(Date fridayPMEnd) {
        this.fridayPMEnd = fridayPMEnd;
    }

    public Date getSaturdayAMStart() {
        return saturdayAMStart;
    }

    public void setSaturdayAMStart(Date saturdayAMStart) {
        this.saturdayAMStart = saturdayAMStart;
    }

    public Date getSaturdayAMEnd() {
        return saturdayAMEnd;
    }

    public void setSaturdayAMEnd(Date saturdayAMEnd) {
        this.saturdayAMEnd = saturdayAMEnd;
    }

    public Date getSaturdayPMStart() {
        return saturdayPMStart;
    }

    public void setSaturdayPMStart(Date saturdayPMStart) {
        this.saturdayPMStart = saturdayPMStart;
    }

    public Date getSaturdayPMEnd() {
        return saturdayPMEnd;
    }

    public void setSaturdayPMEnd(Date saturdayPMEnd) {
        this.saturdayPMEnd = saturdayPMEnd;
    }

    public Date getSundayAMStart() {
        return sundayAMStart;
    }

    public void setSundayAMStart(Date sundayAMStart) {
        this.sundayAMStart = sundayAMStart;
    }

    public Date getSundayAMEnd() {
        return sundayAMEnd;
    }

    public void setSundayAMEnd(Date sundayAMEnd) {
        this.sundayAMEnd = sundayAMEnd;
    }

    public Date getSundayPMStart() {
        return sundayPMStart;
    }

    public void setSundayPMStart(Date sundayPMStart) {
        this.sundayPMStart = sundayPMStart;
    }

    public Date getSundayPMEnd() {
        return sundayPMEnd;
    }

    public void setSundayPMEnd(Date sundayPMEnd) {
        this.sundayPMEnd = sundayPMEnd;
    }
}
