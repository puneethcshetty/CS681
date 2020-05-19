package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class AutoSaver extends File implements Runnable{

	boolean done=false;

	ReentrantLock lock=new ReentrantLock(); 

	protected void setDone() {
		lock.lock();
		try {
			done = true;			
		} finally {
			lock.unlock();
		}
	}

	public void run() {
		System.out.println("Starting AutoSave");
		while(true){
			lock.lock();
			try {
				if(done){
					System.out.println("Exiting AutoSaver");
					break;
				} 
				save();
				try {
					Thread.sleep(100);
				}catch(Exception e) {
					System.out.println("There is an exception in AutoSaver: "+e);
				}
			}finally {
				lock.unlock();
			}
		}
	}
}
