package com.deqingbank.cloud.task.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class TaskObserver implements Observerable {
	
	private List<Observer> list;
	
	private String message;
	
	public TaskObserver() {
		list = new ArrayList<Observer>();
	}

	@Override
	public void registerObserver(Observer o) {
		list.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		if(!CollectionUtils.isEmpty(list)) {
			list.remove(o);
		}
	}

	@Override
	public void notifyObserver() {
		for(int i = 0; i < list.size(); i++) {
            Observer oserver = list.get(i);
            oserver.update(message);
        }
	}

}
