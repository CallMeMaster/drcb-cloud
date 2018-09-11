package com.deqingbank.cloud.task.entity;

public enum TaskState {

	PROCESSING("runing",1),PENDING("pending",2),ERROR("error",4);
	
	private String name;
	private Integer value;
	
	private TaskState(String name,Integer value) {
		this.name = name;
		this.value = value;
	}
}
