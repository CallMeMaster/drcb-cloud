package com.deqingbank.cloud.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deqingbank.cloud.task.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
}
