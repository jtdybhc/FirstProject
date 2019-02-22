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
 * ��Դ����
 * 
 */

class Resource{
	
	private static int num=0;			//��ǰ��Դ����
	
	/*
	 * ������Դ
	 */
	
	public synchronized void remove() {
		if(num>0) {
			num--;
			System.out.println("������"+Thread.currentThread().getName()+"����һ����Դ");
			notifyAll();
		}else {
			try {
				wait();
				System.out.println("������"+Thread.currentThread().getName()+"����ȴ�״̬");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * ������Դ
	 */
	
	public synchronized void add() {
		num++;
		System.out.println("������"+Thread.currentThread().getName()+"����һ����Դ");
		notifyAll();
	}
}

/*
 * 
 * �������߳���
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
 * �������߳���
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