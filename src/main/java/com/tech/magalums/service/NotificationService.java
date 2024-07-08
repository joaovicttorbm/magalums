package com.tech.magalums.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tech.magalums.controller.dto.ScheduleNotificationDto;
import com.tech.magalums.entity.Notification;
import com.tech.magalums.entity.Status;
import com.tech.magalums.repository.NotificationRepository; 

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(ScheduleNotificationDto dto) {
        notificationRepository.save(dto.toNotification());
    }

    public Optional<Notification> findById(Long notificationId) {
        return notificationRepository.findById(notificationId);
    }

    public void cancelNotification(Long notificationId) {
        findById(notificationId).ifPresent(notification -> {
            notification.setStatus(Status.Values.CANCELED.toStatus());
            notificationRepository.save(notification);
        });
    }

    public void checkAndSend(LocalDateTime dateTime) {
        var notifications = notificationRepository.findByStatusInAndDateTimeBefore(
                List.of(Status.Values.PENDING.toStatus(), Status.Values.ERROR.toStatus()),
                dateTime
        );

        notifications.forEach(this::sendNotification);
    }

    private void sendNotification(Notification notification) {
        notification.setStatus(Status.Values.SUCCESS.toStatus());
        notificationRepository.save(notification);
    }
}
