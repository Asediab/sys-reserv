package com.sys.establishment.dto;

import java.io.Serializable;
import java.time.LocalTime;

public class TimeTableDTO implements Serializable {

    private Long id;

    private Long establishmentId;

    private LocalTime mondayAMStart;
    private LocalTime mondayAMEnd;

    private LocalTime mondayPMStart;
    private LocalTime mondayPMEnd;

    private LocalTime tuesdayAMStart;
    private LocalTime tuesdayAMEnd;

    private LocalTime tuesdayPMStart;
    private LocalTime tuesdayPMEnd;

    private LocalTime wednesdayAMStart;
    private LocalTime wednesdayAMEnd;

    private LocalTime wednesdayPMStart;
    private LocalTime wednesdayPMEnd;

    private LocalTime thursdayAMStart;
    private LocalTime thursdayAMEnd;

    private LocalTime thursdayPMStart;
    private LocalTime thursdayPMEnd;

    private LocalTime fridayAMStart;
    private LocalTime fridayAMEnd;

    private LocalTime fridayPMStart;
    private LocalTime fridayPMEnd;

    private LocalTime saturdayAMStart;
    private LocalTime saturdayAMEnd;

    private LocalTime saturdayPMStart;
    private LocalTime saturdayPMEnd;

    private LocalTime sundayAMStart;
    private LocalTime sundayAMEnd;

    private LocalTime sundayPMStart;
    private LocalTime sundayPMEnd;

    public TimeTableDTO() {
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
