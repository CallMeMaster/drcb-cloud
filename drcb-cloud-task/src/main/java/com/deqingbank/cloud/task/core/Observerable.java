package com.deqingbank.cloud.task.core;

public interface Observerable {

	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObserver();
}
