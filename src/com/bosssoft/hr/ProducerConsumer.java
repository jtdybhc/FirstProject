package com.bosssoft.hr;

public class ProducerConsumer {

	public static void main(String[] args) {
		
		Resource r1=new Resource();
		
		ProducerThread p1=new ProducerThread(r1);
		ProducerThread p2=new ProducerThread(r1);
		ProducerThread p3=new ProducerThread(r1);
		ProducerThread p4=new ProducerThread(r1);
		ProducerThread p5=new ProducerThread(r1);
		
		ConsumerThread c1=new ConsumerThread(r1);
		ConsumerThread c2=new ConsumerThread(r1);
//		ConsumerThread c3=new ConsumerThread(r1);
//		ConsumerThread c4=new ConsumerThread(r1);
//		ConsumerThread c5=new ConsumerThread(r1);
	
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();
		
		c1.start();
		c2.start();
//		c3.start();
//		c4.start();
//		c5.start();
	
	}
	
}

/*
 * 资源池类
 * 
 */

class Resource{
	
	private static int num=0;			//当前资源数量
	
	/*
	 * 消耗资源
	 */
	
	public synchronized void remove() {
		if(num>0) {
			num--;
			System.out.println("消费者"+Thread.currentThread().getName()+"消耗一件资源");
			notifyAll();
		}else {
			try {
				wait();
				System.out.println("消费者"+Thread.currentThread().getName()+"进入等待状态");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 生产资源
	 */
	
	public synchronized void add() {
		num++;
		System.out.println("生产者"+Thread.currentThread().getName()+"生产一件资源");
		notifyAll();
	}
}

/*
 * 
 * 生产者线程类
 */

class ProducerThread extends Thread{
	
	private Resource resource;
	public ProducerThread(Resource resource) {
		this.resource=resource;
	}
	
	public void run() {
		
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		resource.add();
		}
		
	}
	
}

/*
 * 
 * 消费者线程类
 */

class ConsumerThread extends Thread{
	private Resource resource;
	public ConsumerThread(Resource resource) {
		this.resource=resource;
	}
	
	public void run() {
		
		while (true) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			resource.remove();
			
		}
	}	
}