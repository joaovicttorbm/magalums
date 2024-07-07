package com.tech.magalums.repository;

// import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.magalums.entity.Notification;

public interface NotificationRespository extends JpaRepository<Notification, Long> {
    // List<Object><Notification> findByStatusInAndDateTimeBefore(List<Status> status, LocalDateTime dateTime);
}
