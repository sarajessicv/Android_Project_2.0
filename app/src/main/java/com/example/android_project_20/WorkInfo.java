package com.example.android_project_20;

import java.sql.Date;
import java.sql.Time;

public class WorkInfo {

    Date date;
    Time startTime;
    Time endTime;

    public WorkInfo() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

}
