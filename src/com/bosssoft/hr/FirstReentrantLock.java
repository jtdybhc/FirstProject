package com.bosssoft.hr;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class FirstReentrantLock {
	
	
	public static void main(String[] args) {
		
		Runnable runnable = new ReentrantLockThread();
		new Thread(runnable,"A").start();
		new Thread(runnable,"B").start();
		
	}

}

class ReentrantLockThread implements Runnable{
	
	ReentrantLock lock=new ReentrantLock();
	
	public static int i=0;
	ArrayList arrayList = new ArrayList();
	
	public void run() {
		while (true) {
			try {
				lock.lock();
				for (int j = 0; j < 3; j++) {
					arrayList.add(i);
					System.out.println(Thread.currentThread().getName()+"²åÈëÁË"+i);
					i++;
				}
				
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			} finally {
				
				lock.unlock();
				
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}