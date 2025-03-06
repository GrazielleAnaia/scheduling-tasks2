package com.grazielleanaia.scheduling_tasks2.controller;


import com.grazielleanaia.scheduling_tasks2.business.TasksService;
import com.grazielleanaia.scheduling_tasks2.business.dto.TasksDTO;
import com.grazielleanaia.scheduling_tasks2.infrastructure.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")

@RequiredArgsConstructor
public class TasksController {

    public final TasksService tasksService;

    @PostMapping
    public ResponseEntity<TasksDTO> createTask(@RequestBody TasksDTO tasksDTO,
                                               @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tasksService.createTask(token, tasksDTO));
    }

    @GetMapping
    public ResponseEntity<List<TasksDTO>> searchTasksByEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tasksService.searchTasksByEmail(token));
    }

    @GetMapping("/events")
    public ResponseEntity<List<TasksDTO>> searchTasksByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                              LocalDateTime initialDate,
                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                              LocalDateTime finalDate) {
        return ResponseEntity.ok(tasksService.searchTasksByPeriod(initialDate, finalDate));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTaskById(@RequestParam("id") String id) {
        tasksService.deleteTaskById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<TasksDTO> updateTasks(@RequestBody TasksDTO tasksDTO,
                                                @RequestParam("id") String id) {
        return ResponseEntity.ok(tasksService.updateTasks(tasksDTO, id));

    }

    @PatchMapping
    public ResponseEntity<TasksDTO> changeNotificationStatus(@RequestParam("id") String id,
                                                             @RequestParam("status") NotificationStatusEnum status) {
        return ResponseEntity.ok(tasksService.changeNotificationStatus(id, status));
    }


}
