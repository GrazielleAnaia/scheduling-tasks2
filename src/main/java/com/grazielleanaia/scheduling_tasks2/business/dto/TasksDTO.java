package com.grazielleanaia.scheduling_tasks2.business.dto;


import com.grazielleanaia.scheduling_tasks2.infrastructure.enums.NotificationStatusEnum;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder

public class TasksDTO {

    private String id;

    private String customerEmail;

    private String taskName;

    private String description;

    private LocalDateTime creationDate;

    private LocalDateTime eventDate;

    private LocalDateTime changeDate;

    private NotificationStatusEnum notificationStatusEnum;
}
