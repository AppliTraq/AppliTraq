package com.appliboard.appliboard.repositories;

import com.appliboard.appliboard.models.Note;
import com.appliboard.appliboard.models.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    Reminder findById(long id);

    @Query("FROM Reminder r WHERE r.title LIKE %:query% OR r.description LIKE %:query%")
    List<Reminder> findAllQuery(String query);

    List<Reminder> findRemindersByJobApplication_Id(long id);
}
