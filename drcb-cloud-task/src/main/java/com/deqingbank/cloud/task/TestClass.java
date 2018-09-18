package com.deqingbank.cloud.task;

public class TestClass {

	private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    
	public static void main(String[] args) {
		System.out.println(CAPACITY);
	}
}
