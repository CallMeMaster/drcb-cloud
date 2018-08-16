package com.deqingbank.cloud.task.test;

import org.springframework.core.task.TaskExecutor;

public class TaskExecutorExample {
	
	private TaskExecutor taskExecutor;
	
	public TaskExecutorExample(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	
	public void printMessage() {
		for(int i=0;i<20;i++) {
			taskExecutor.execute(new MessagePrinterTask("Message:"+i));
		}
	}
	
	private class MessagePrinterTask implements Runnable {

		private String message;
		
		@Override
		public void run() {
			System.out.println(message);
		}
		
		public MessagePrinterTask(String message) {
			this.message = message;
		}
	}
}
