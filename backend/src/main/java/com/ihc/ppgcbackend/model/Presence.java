package com.ihc.ppgcbackend.model;

import org.springframework.data.annotation.Id;

public class Presence {

    @Id
    private String id;

    private int day;
    private int month;
    private int year;

    private Status status;
    private String reason;

    private String userId;

    public Presence(int day, int month, int year, Status status, String reason, String userId) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.status = status;
        this.reason = reason;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
