package com.grazielleanaia.scheduling_tasks2.infrastructure.repository;

import com.grazielleanaia.scheduling_tasks2.infrastructure.entity.Tasks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends MongoRepository<Tasks, String> {
}
