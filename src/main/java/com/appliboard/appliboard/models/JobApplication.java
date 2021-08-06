package com.appliboard.appliboard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="jobApplications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1500)
    private String description;

    @Column(nullable = false)
    private String company;

    @Column()
    private double salary;

    @Column()
    private String location;

    @Column()
    private String logo;

// FIX THIS WEIRD ERROR , AND WHAT'S THIS MAPPEDBY THING
    @ManyToOne(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Timeline> timeline;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobApplications")
    private List<Reminder> reminders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobApplications")
    private List<Note> notes;

    public JobApplication () {}

    public JobApplication(long id, User user, String title, String description, String company, double salary, String location, String logo) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.company = company;
        this.salary = salary;
        this.location = location;
        this.logo = logo;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
