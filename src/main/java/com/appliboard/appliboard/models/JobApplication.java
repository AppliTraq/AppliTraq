package com.appliboard.appliboard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "jobApplications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 15000)
    private String description;

    @Column(nullable = false)
    private String company;

    @Column()
    private Integer salary;

    @Column()
    private String location;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "timeline_id")
    private List<Timeline> timeline_status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobApplication")
    private List<Reminder> reminders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobApplication")
    private List<Note> notes;

    public JobApplication() {
    }


    // THIS CONSTRUCTOR IS MISSING A TIMELINE OBJECT BEING PASSED ON SO WE CAN CREATE A GETTER AND SETTER OF THE TIMELINE
    public JobApplication(long id, User user, String title, String description, String company, Integer salary,
                          String location, List<Timeline> timeline_status) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.company = company;
        this.salary = salary;
        this.location = location;
        this.timeline_status = timeline_status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Timeline> getTimeline() {
        return timeline_status;
    }

    public void setTimeline(List<Timeline> timeline_status) {
        this.timeline_status = timeline_status;
    }

}

