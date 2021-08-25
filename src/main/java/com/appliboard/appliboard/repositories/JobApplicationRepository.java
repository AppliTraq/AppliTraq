package com.appliboard.appliboard.repositories;

import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.models.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository <JobApplication, Long> {
//    @Query("FROM JobApplication a WHERE a.id = ?1")
    JobApplication findById(long id);

    @Query("FROM JobApplication j WHERE j.title LIKE %:query% OR j.description LIKE %:query% OR j.company LIKE %:query%")
    List<JobApplication> findAllQuery(String query);

    List<JobApplication> findJobApplicationsByUserId(long id);

}
