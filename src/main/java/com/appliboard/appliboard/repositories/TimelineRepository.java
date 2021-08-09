package com.appliboard.appliboard.repositories;

import com.appliboard.appliboard.models.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimelineRepository extends JpaRepository<Timeline, Long>{
    // this repository interface is being connected to the Timeline class ; manages data between Java objects and a relational database.

    Timeline findbyId(long id);
}