package com.tech.magalums.controller.dto;

import java.time.LocalDateTime;

import com.tech.magalums.entity.Channel;
import com.tech.magalums.entity.Notification;
import com.tech.magalums.entity.Status;

public record ScheduleNotificationDto(
        LocalDateTime dateTime,
        String destination,
        String message,
        Channel.Values channel) {

    public Notification toNotification() {
        Channel channelObj = channel.toChannel();
        if (channelObj == null) {
            throw new IllegalStateException("Invalid channel value: " + channel);
        }
        return new Notification(
                dateTime,
                destination,
                message,
                channelObj,
                Status.Values.PENDING.toStatus()
        );
    }
}
