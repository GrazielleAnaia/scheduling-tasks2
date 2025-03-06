package com.grazielleanaia.scheduling_tasks2.business;


import com.grazielleanaia.scheduling_tasks2.business.converter.TasksConverter;
import com.grazielleanaia.scheduling_tasks2.business.dto.TasksDTO;
import com.grazielleanaia.scheduling_tasks2.infrastructure.entity.Tasks;
import com.grazielleanaia.scheduling_tasks2.infrastructure.enums.NotificationStatusEnum;
import com.grazielleanaia.scheduling_tasks2.infrastructure.exception.ResourceNotFoundException;
import com.grazielleanaia.scheduling_tasks2.infrastructure.repository.TasksRepository;
import com.grazielleanaia.scheduling_tasks2.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

@RequiredArgsConstructor
public class TasksService {

    private final TasksConverter tasksConverter;
    private final TasksRepository tasksRepository;
    private final JwtUtil jwtUtil;

    public TasksDTO createTask(String token, TasksDTO tasksDTO) {
        String email = jwtUtil.extractUsername(token.substring(7));
        tasksDTO.setCustomerEmail(email);
        tasksDTO.setCreationDate(LocalDateTime.now());
        tasksDTO.setNotificationStatusEnum(NotificationStatusEnum.PENDENT);
        Tasks tasks = tasksConverter.convertToTasks(tasksDTO);
        return tasksConverter.convertToTasksDTO(tasksRepository.save(tasks));
    }

    public void deleteTaskById(String id) {
        try {
            tasksRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Id not found." + id, e.getCause());
        }
    }

    public List<TasksDTO> searchTasksByEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        List<Tasks> tasksList = tasksRepository.findByCustomerEmail(email);
        return tasksConverter.convertToTaskDTOList(tasksList);
    }

    public List<TasksDTO> searchTasksByPeriod(LocalDateTime initialDate, LocalDateTime finalDate) {
        List<Tasks> tasksList = tasksRepository.findByEventDateBetween(initialDate, finalDate);
        return tasksConverter.convertToTaskDTOList(tasksList);
    }



    public TasksDTO updateTasks(TasksDTO tasksDTO, String idTask) {
        try {
            Tasks tasks = tasksRepository.findById(idTask).orElseThrow(() ->
                    new ResourceNotFoundException("Id not found" + idTask));
            tasksDTO.setCreationDate(LocalDateTime.now());
            Tasks tasks1 = tasksConverter.updateTasks(tasksDTO, tasks);
            return tasksConverter.convertToTasksDTO(tasksRepository.save(tasks1));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error to alterate tasks." + idTask, e.getCause());
        }
    }

    public TasksDTO changeNotificationStatus(String idTask, NotificationStatusEnum status) {
        try{
            Tasks tasks = tasksRepository.findById(idTask).orElseThrow(() ->
                    new ResourceNotFoundException("Task id not found." + idTask));
         tasks.setNotificationStatusEnum(status);
         return tasksConverter.convertToTasksDTO(tasksRepository.save(tasks));

        } catch (ResourceNotFoundException e ) {
            throw new ResourceNotFoundException("Error to alterate status", e.getCause());
        }
    }


}
