package com.appliboard.appliboard.repositories;

import com.appliboard.appliboard.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Note findById(long id);
}
