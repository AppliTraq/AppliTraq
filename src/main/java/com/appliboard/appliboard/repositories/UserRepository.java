package com.appliboard.appliboard.repositories;

import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    User findByUsername(String username);
}
