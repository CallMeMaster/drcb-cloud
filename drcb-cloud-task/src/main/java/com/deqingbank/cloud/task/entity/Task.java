package com.deqingbank.cloud.task.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Task {

	@Id
	@GeneratedValue
	private Long id;
	private String type;
	private String name;
	private String url;
}
