package com.deqingbank.cloud.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deqingbank.cloud.task.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	
	@Query("update Task t set t.name=:name,t.url=:url,t.cron=:cron where t.id=:id")
	void update(@Param("id") Long id,@Param("name") String name,@Param("cron") String cron,@Param("url") String url);
}
