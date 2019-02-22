package com.bosssoft.hr;

public class KnifeFork {

	public static String knife="刀";
	public static String fork="叉子";
	
	public static void main(String[] args) {
		
		Thread thread1=new Thread(new Lock1());
		Thread thread2=new Thread(new Lock2());
		thread1.start();
		thread2.start();
	}

}

class Lock1 implements Runnable{
	public void run() {
		try {
			System.out.println("lock1启动");
			while (true) {
				synchronized (KnifeFork.knife) {
					System.out.println("Lock1拿到了刀");
					Thread.sleep(3000);
					synchronized (KnifeFork.fork) {
						System.out.println("Lock拿到了叉子");
						Thread.sleep(100000);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}

class Lock2 implements Runnable{
	public void run() {
		
		try {
			System.out.println("lock2启动");
			while (true) {
				synchronized (KnifeFork.fork) {
					System.out.println("Lock2拿到了叉子");
					Thread.sleep(3000);
					synchronized (KnifeFork.knife) {
						System.out.println("Lock拿到了刀");
						Thread.sleep(100000);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}