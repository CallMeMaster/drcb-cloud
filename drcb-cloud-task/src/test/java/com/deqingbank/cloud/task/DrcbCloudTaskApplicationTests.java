package com.deqingbank.cloud.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import com.deqingbank.cloud.task.test.TaskExecutorExample;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrcbCloudTaskApplicationTests {
	
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testThreadPoolTaskExecutor() {
		System.out.println("------"+threadPoolTaskExecutor.getCorePoolSize());
		TaskExecutorExample t = new TaskExecutorExample(threadPoolTaskExecutor);
		t.printMessage();
	}

}
