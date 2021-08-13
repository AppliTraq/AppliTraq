package com.appliboard.appliboard.repositories;

import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Note findById(long id);
    long getById(long id);

    @Query("FROM JobApplication n WHERE n.title LIKE %:query%")
//            " OR n.content LIKE %:query%)"
    List<JobApplication> findAllQuery(String query);

    List<Note> findNotesByJobApplicationId(long id);
}
