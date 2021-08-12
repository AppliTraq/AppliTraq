package com.appliboard.appliboard.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reminders")
public class  Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reminder_id;

    @ManyToOne
    @JoinColumn(name="job_id")
    private JobApplication jobApplication;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    public Reminder(){}

    public Reminder(long reminder_id, JobApplication jobApplication, String description, String title) {
        this.reminder_id = reminder_id;
        this.jobApplication = jobApplication;
        this.description = description;
        this.title = title;
    }

    public long getReminder_id() {
        return reminder_id;
    }

    public void setReminder_id(long reminder_id) {
        this.reminder_id = reminder_id;
    }

    public JobApplication getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(JobApplication jobApplication) {
        this.jobApplication = jobApplication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
