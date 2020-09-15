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
    private LocalTime mondayAMStart;
    @NotNull
    @Column(nullable = false)
    private LocalTime mondayAMEnd;

    @NotNull
    @Column(nullable = false)
    private LocalTime mondayPMStart;
    @NotNull
    @Column(nullable = false)
    private LocalTime mondayPMEnd;

    @NotNull
    @Column(nullable = false)
    private LocalTime tuesdayAMStart;
    @NotNull
    @Column(nullable = false)
    private LocalTime tuesdayAMEnd;

    @NotNull
    @Column(nullable = false)
    private LocalTime tuesdayPMStart;
    @NotNull
    @Column(nullable = false)
    private LocalTime tuesdayPMEnd;

    @NotNull
    @Column(nullable = false)
    private LocalTime wednesdayAMStart;
    @NotNull
    @Column(nullable = false)
    private LocalTime wednesdayAMEnd;

    @NotNull
    @Column(nullable = false)
    private LocalTime wednesdayPMStart;
    @NotNull
    @Column(nullable = false)
    private LocalTime wednesdayPMEnd;

    @NotNull
    @Column(nullable = false)
    private LocalTime thursdayAMStart;
    @NotNull
    @Column(nullable = false)
    private LocalTime thursdayAMEnd;

    @NotNull
    @Column(nullable = false)
    private LocalTime thursdayPMStart;
    @NotNull
    @Column(nullable = false)
    private LocalTime thursdayPMEnd;

    @NotNull
    @Column(nullable = false)
    private LocalTime fridayAMStart;
    @NotNull
    @Column(nullable = false)
    private LocalTime fridayAMEnd;

    @NotNull
    @Column(nullable = false)
    private LocalTime fridayPMStart;
    @NotNull
    @Column(nullable = false)
    private LocalTime fridayPMEnd;

    @NotNull
    @Column(nullable = false)
    private LocalTime saturdayAMStart;
    @NotNull
    @Column(nullable = false)
    private LocalTime saturdayAMEnd;

    @NotNull
    @Column(nullable = false)
    private LocalTime saturdayPMStart;
    @NotNull
    @Column(nullable = false)
    private LocalTime saturdayPMEnd;

    @NotNull
    @Column(nullable = false)
    private LocalTime sundayAMStart;
    @NotNull
    @Column(nullable = false)
    private LocalTime sundayAMEnd;

    @NotNull
    @Column(nullable = false)
    private LocalTime sundayPMStart;
    @NotNull
    @Column(nullable = false)
    private LocalTime sundayPMEnd;

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

    public LocalTime getMondayAMStart() {
        return mondayAMStart;
    }

    public void setMondayAMStart(LocalTime mondayAMStart) {
        this.mondayAMStart = mondayAMStart;
    }

    public LocalTime getMondayAMEnd() {
        return mondayAMEnd;
    }

    public void setMondayAMEnd(LocalTime mondayAMEnd) {
        this.mondayAMEnd = mondayAMEnd;
    }

    public LocalTime getMondayPMStart() {
        return mondayPMStart;
    }

    public void setMondayPMStart(LocalTime mondayPMStart) {
        this.mondayPMStart = mondayPMStart;
    }

    public LocalTime getMondayPMEnd() {
        return mondayPMEnd;
    }

    public void setMondayPMEnd(LocalTime mondayPMEnd) {
        this.mondayPMEnd = mondayPMEnd;
    }

    public LocalTime getTuesdayAMStart() {
        return tuesdayAMStart;
    }

    public void setTuesdayAMStart(LocalTime tuesdayAMStart) {
        this.tuesdayAMStart = tuesdayAMStart;
    }

    public LocalTime getTuesdayAMEnd() {
        return tuesdayAMEnd;
    }

    public void setTuesdayAMEnd(LocalTime tuesdayAMEnd) {
        this.tuesdayAMEnd = tuesdayAMEnd;
    }

    public LocalTime getTuesdayPMStart() {
        return tuesdayPMStart;
    }

    public void setTuesdayPMStart(LocalTime tuesdayPMStart) {
        this.tuesdayPMStart = tuesdayPMStart;
    }

    public LocalTime getTuesdayPMEnd() {
        return tuesdayPMEnd;
    }

    public void setTuesdayPMEnd(LocalTime tuesdayPMEnd) {
        this.tuesdayPMEnd = tuesdayPMEnd;
    }

    public LocalTime getWednesdayAMStart() {
        return wednesdayAMStart;
    }

    public void setWednesdayAMStart(LocalTime wednesdayAMStart) {
        this.wednesdayAMStart = wednesdayAMStart;
    }

    public LocalTime getWednesdayAMEnd() {
        return wednesdayAMEnd;
    }

    public void setWednesdayAMEnd(LocalTime wednesdayAMEnd) {
        this.wednesdayAMEnd = wednesdayAMEnd;
    }

    public LocalTime getWednesdayPMStart() {
        return wednesdayPMStart;
    }

    public void setWednesdayPMStart(LocalTime wednesdayPMStart) {
        this.wednesdayPMStart = wednesdayPMStart;
    }

    public LocalTime getWednesdayPMEnd() {
        return wednesdayPMEnd;
    }

    public void setWednesdayPMEnd(LocalTime wednesdayPMEnd) {
        this.wednesdayPMEnd = wednesdayPMEnd;
    }

    public LocalTime getThursdayAMStart() {
        return thursdayAMStart;
    }

    public void setThursdayAMStart(LocalTime thursdayAMStart) {
        this.thursdayAMStart = thursdayAMStart;
    }

    public LocalTime getThursdayAMEnd() {
        return thursdayAMEnd;
    }

    public void setThursdayAMEnd(LocalTime thursdayAMEnd) {
        this.thursdayAMEnd = thursdayAMEnd;
    }

    public LocalTime getThursdayPMStart() {
        return thursdayPMStart;
    }

    public void setThursdayPMStart(LocalTime thursdayPMStart) {
        this.thursdayPMStart = thursdayPMStart;
    }

    public LocalTime getThursdayPMEnd() {
        return thursdayPMEnd;
    }

    public void setThursdayPMEnd(LocalTime thursdayPMEnd) {
        this.thursdayPMEnd = thursdayPMEnd;
    }

    public LocalTime getFridayAMStart() {
        return fridayAMStart;
    }

    public void setFridayAMStart(LocalTime fridayAMStart) {
        this.fridayAMStart = fridayAMStart;
    }

    public LocalTime getFridayAMEnd() {
        return fridayAMEnd;
    }

    public void setFridayAMEnd(LocalTime fridayAMEnd) {
        this.fridayAMEnd = fridayAMEnd;
    }

    public LocalTime getFridayPMStart() {
        return fridayPMStart;
    }

    public void setFridayPMStart(LocalTime fridayPMStart) {
        this.fridayPMStart = fridayPMStart;
    }

    public LocalTime getFridayPMEnd() {
        return fridayPMEnd;
    }

    public void setFridayPMEnd(LocalTime fridayPMEnd) {
        this.fridayPMEnd = fridayPMEnd;
    }

    public LocalTime getSaturdayAMStart() {
        return saturdayAMStart;
    }

    public void setSaturdayAMStart(LocalTime saturdayAMStart) {
        this.saturdayAMStart = saturdayAMStart;
    }

    public LocalTime getSaturdayAMEnd() {
        return saturdayAMEnd;
    }

    public void setSaturdayAMEnd(LocalTime saturdayAMEnd) {
        this.saturdayAMEnd = saturdayAMEnd;
    }

    public LocalTime getSaturdayPMStart() {
        return saturdayPMStart;
    }

    public void setSaturdayPMStart(LocalTime saturdayPMStart) {
        this.saturdayPMStart = saturdayPMStart;
    }

    public LocalTime getSaturdayPMEnd() {
        return saturdayPMEnd;
    }

    public void setSaturdayPMEnd(LocalTime saturdayPMEnd) {
        this.saturdayPMEnd = saturdayPMEnd;
    }

    public LocalTime getSundayAMStart() {
        return sundayAMStart;
    }

    public void setSundayAMStart(LocalTime sundayAMStart) {
        this.sundayAMStart = sundayAMStart;
    }

    public LocalTime getSundayAMEnd() {
        return sundayAMEnd;
    }

    public void setSundayAMEnd(LocalTime sundayAMEnd) {
        this.sundayAMEnd = sundayAMEnd;
    }

    public LocalTime getSundayPMStart() {
        return sundayPMStart;
    }

    public void setSundayPMStart(LocalTime sundayPMStart) {
        this.sundayPMStart = sundayPMStart;
    }

    public LocalTime getSundayPMEnd() {
        return sundayPMEnd;
    }

    public void setSundayPMEnd(LocalTime sundayPMEnd) {
        this.sundayPMEnd = sundayPMEnd;
    }
}
