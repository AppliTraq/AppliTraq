package com.appliboard.appliboard.repositories;

import com.appliboard.appliboard.models.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    Reminder findById(long id);

}
