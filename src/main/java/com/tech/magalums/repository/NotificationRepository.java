package com.tech.magalums.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.magalums.entity.Notification;
import com.tech.magalums.entity.Status;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
    List<Notification> findByStatusInAndDateTimeBefore(List<Status> status, LocalDateTime dateTime);
}

