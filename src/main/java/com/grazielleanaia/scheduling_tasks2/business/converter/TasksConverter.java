package com.grazielleanaia.scheduling_tasks2.business.converter;


import com.grazielleanaia.scheduling_tasks2.business.dto.TasksDTO;
import com.grazielleanaia.scheduling_tasks2.infrastructure.entity.Tasks;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TasksConverter {

    public TasksDTO convertToTasksDTO(Tasks tasks) {
        return TasksDTO.builder()
                .id(tasks.getId())
                .taskName(tasks.getTaskName())
                .creationDate(tasks.getCreationDate())
                .customerEmail(tasks.getCustomerEmail())
                .changeDate(tasks.getChangeDate())
                .eventDate(tasks.getEventDate())
                .description(tasks.getDescription())
                .notificationStatusEnum(tasks.getNotificationStatusEnum())
                .build();
    }

    public Tasks convertToTasks(TasksDTO tasksDTO) {
        return Tasks.builder()
                .taskName(tasksDTO.getTaskName())
                .creationDate(tasksDTO.getCreationDate())
                .customerEmail(tasksDTO.getCustomerEmail())
                .changeDate(tasksDTO.getChangeDate())
                .eventDate(tasksDTO.getEventDate())
                .description(tasksDTO.getDescription())
                .notificationStatusEnum(tasksDTO.getNotificationStatusEnum())
                .build();
    }

    public List<TasksDTO> convertToTaskDTOList(List<Tasks> taskList) {
        return taskList.stream().map(this::convertToTasksDTO).toList();
    }

    public List<Tasks> convertToTaskList(List<TasksDTO> tasksDTO) {
        return tasksDTO.stream().map(this::convertToTasks).toList();
    }

    public Tasks updateTasks(TasksDTO tasksDTO, Tasks tasks) {
        return Tasks.builder()
                .taskName(tasksDTO.getTaskName() != null ? tasksDTO.getTaskName() : tasks.getTaskName())
                .description(tasksDTO.getDescription() != null ? tasksDTO.getDescription() : tasks.getDescription())
                .customerEmail(tasksDTO.getCustomerEmail() != null ? tasksDTO.getCustomerEmail() : tasks.getCustomerEmail())
                .eventDate(tasksDTO.getEventDate() != null ? tasksDTO.getEventDate() : tasks.getEventDate())
                .changeDate(tasksDTO.getChangeDate() != null ? tasksDTO.getChangeDate() : tasks.getChangeDate())
                .notificationStatusEnum(tasksDTO.getNotificationStatusEnum() != null ? tasksDTO.getNotificationStatusEnum() : tasks.getNotificationStatusEnum())
                .id(tasks.getId())
                .creationDate(tasksDTO.getCreationDate() != null ? tasksDTO.getCreationDate() : tasks.getCreationDate())
                .build();

    }
}
