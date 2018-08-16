package com.deqingbank.cloud.task.test;

public class AttendRecordDownloadTask implements Runnable{

	private String ipaddr;
	
	public AttendRecordDownloadTask(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	
	@Override
	public void run() {
		System.out.println("Start to download from:"+ipaddr+"!");
		if(ipaddr.equals("Machine10") || ipaddr.equals("Machine4")) {
			try {
				System.out.println("======sleep");
				Thread.sleep(10000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("finish to download from:"+ipaddr+"!");
	}
}
