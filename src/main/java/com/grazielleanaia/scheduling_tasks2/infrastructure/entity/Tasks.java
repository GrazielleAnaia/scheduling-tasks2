package com.grazielleanaia.scheduling_tasks2.infrastructure.entity;


import com.grazielleanaia.scheduling_tasks2.infrastructure.enums.NotificationStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document("tasks")

public class Tasks {

    @Id
    private String id;

    private String customerEmail;

    private String taskName;

    private String description;

    private LocalDateTime creationDate;

    private LocalDateTime eventDate;

    private LocalDateTime changeDate;

    private NotificationStatusEnum notificationStatusEnum;




}
