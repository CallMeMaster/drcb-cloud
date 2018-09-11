package com.deqingbank.cloud.task.entity;

public enum TaskState {

	processing("runing",1),pending("pending",2),add("new",0),error("error",4);
	
	private String name;
	private Integer value;
	
	private TaskState(String name,Integer value) {
		this.name = name;
		this.value = value;
	}
}
