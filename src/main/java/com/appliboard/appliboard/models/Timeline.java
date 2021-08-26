package com.appliboard.appliboard.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "timeline")
public class Timeline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long timeline_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobApplications")
    private JobApplication jobApplications;

    @Column(nullable = false, columnDefinition = "DATE")
    private Date date;

    @Column(nullable = false)
    private int kanbanStatus;

    public Timeline() {}

    public Timeline(long timeline_id, JobApplication jobApplications, Date date, int kanbanStatus) {
        this.timeline_id = timeline_id;
        this.jobApplications = jobApplications;
        this.date = date;
        this.kanbanStatus = kanbanStatus;
    }

//Created contstructor without ID because it is auto-generated for use in the controller
    public Timeline(JobApplication jobApplications, Date date, int kanbanStatus) {
        this.jobApplications = jobApplications;
        this.date = date;
        this.kanbanStatus = kanbanStatus;
    }

    public long getTimeline_id() {
        return timeline_id;
    }

    public void setTimeline_id(long timeline_id) {
        this.timeline_id = timeline_id;
    }

    public JobApplication getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(JobApplication jobApplications) {
        this.jobApplications = jobApplications;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getKanbanStatus() {
        return kanbanStatus;
    }

    public void setKanbanStatus(int kanbanStatus) {
        this.kanbanStatus = kanbanStatus;
    }
}
