package com.deqingbank.cloud.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deqingbank.cloud.task.entity.Task;
import com.deqingbank.cloud.task.entity.TaskState;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	@Modifying
	@Query("update Task t set t.state=:state where t.id=:id")
	void setState(@Param("id") Long id, @Param("state") TaskState state);
	
	@Modifying
	@Query("update Task t set t.name=:name,t.cron=:cron,t.url=:url where t.id=:id")
	void update(@Param("id") Long id,@Param("name") String name,@Param("cron") String cron,@Param("url") String url);
}
