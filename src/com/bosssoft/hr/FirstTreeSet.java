package com.bosssoft.hr;

import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class FirstTreeSet {

	public static void main(String[] args) {
		
		TreeSet t=new TreeSet();
		Random random = new Random();
		
		while(t.size()<100) {
			int sid=random.nextInt(1000)+1;
			int age=random.nextInt(30)+5;
			t.add(new Student(sid, age));
		}
		
		Iterator it=t.iterator();
		while (it.hasNext()) {
			
			Student s=(Student) it.next();
			System.out.println("学号："+s.getSid()+"  姓名："+s.getName()+"  年龄："+s.getAge()+"  性别"+s.getSex());
			
		}
	}
}

class Student implements Comparable{
	private int sid;
	private String name="XXX";
	private int age;
	private String sex="x";
	
	public Student(int sid,int age) {
		
		this.sid=sid;
		this.age=age;
		
	}
	
	
	public int getSid() {
		return sid;
	}
	
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getSex() {
		return sex;
	}

	public int compareTo(Object o) {
		
		Student s = (Student) o;
		
		if (this.sid<s.sid) {
			return -1;
		}else if (this.sid>s.sid) {
			return 1;
		} 
		
		else {
			if (this.age<s.age) {
				return -1;
			}else if (this.age>s.age) {
				return 1;
			} 
			else {
				return 0;
			}

		}
	}
	
	
	
	
	
}