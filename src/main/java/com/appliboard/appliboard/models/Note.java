package com.appliboard.appliboard.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long note_id;

    @Column(nullable = true, length = 50)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, columnDefinition = "DATETIME")
    @CreationTimestamp
    private Date date;

    @ManyToOne
    @JoinColumn(name="job_id")
    private JobApplication jobApplication;

    public Note() {
    }

    public Note(long note_id, String title, String content, Date date, JobApplication jobApplication) {
        this.note_id = note_id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.jobApplication = jobApplication;
    }

    public long getNote_id() {
        return note_id;
    }

    public void setNote_id(long note_id) {
        this.note_id = note_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public JobApplication getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(JobApplication jobApplication) {
        this.jobApplication = jobApplication;
    }
}
