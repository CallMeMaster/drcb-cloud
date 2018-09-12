package com.deqingbank.cloud.task.entity;

public enum TaskState {

	PROCESSING("runing",0),PENDING("pending",1),ERROR("error",2),CANCEL("error",3);
	
	private String name;
	private Integer value;
	
	private TaskState(String name,Integer value) {
		this.name = name;
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
	
}
