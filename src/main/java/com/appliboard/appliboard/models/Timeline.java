package com.appliboard.appliboard.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "timeline")
public class Timeline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long timeline_id;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "timeline")
    private List<JobApplication> jobApplications;

    @Column(nullable = false, columnDefinition = "DATE")
    private Date date;

    @Column(nullable = false)
    private int kanban_status;

    // constructors
    public Timeline() {
    }

    public Timeline(long timeline_id, List<JobApplication> jobApplications, Date date, int kanban_status) {
        this.timeline_id = timeline_id;
        this.jobApplications = jobApplications;
        this.date = date;
        this.kanban_status = kanban_status;
    }

    // setters and getters
    public long getTimeline_id() {
        return timeline_id;
    }

    public void setTimeline_id(long timeline_id) {
        this.timeline_id = timeline_id;
    }

    public List<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(List<JobApplication> jobApplications) {
        this.jobApplications = jobApplications;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getKanban_status() {
        return kanban_status;
    }

    public void setKanban_status(int kanban_status) {
        this.kanban_status = kanban_status;
    }
}
