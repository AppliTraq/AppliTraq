package com.appliboard.appliboard.repositories;

import com.appliboard.appliboard.models.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobApplicationRepository extends JpaRepository <JobApplication, Long> {
//    @Query("FROM JobApplication a WHERE a.id = ?1")
    JobApplication findById(long id);

//    @Query("FROM JobApplication a WHERE a.title LIKE %:term%")
    JobApplication findFirstByTitle(String term);
}
