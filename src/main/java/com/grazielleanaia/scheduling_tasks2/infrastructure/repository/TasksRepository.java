package com.grazielleanaia.scheduling_tasks2.infrastructure.repository;

import com.grazielleanaia.scheduling_tasks2.infrastructure.entity.Tasks;
import org.springframework.cglib.core.Local;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TasksRepository extends MongoRepository<Tasks, String> {

    List<Tasks> findByCustomerEmail(String email);

    List<Tasks> findByEventDateBetween(LocalDateTime initialDate, LocalDateTime finalDate);
}
