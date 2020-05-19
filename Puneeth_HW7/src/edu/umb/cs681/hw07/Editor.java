package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class Editor extends File implements Runnable {

	boolean  done=false;
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
		System.out.println("Starting Editor");
		while(true){
			lock.lock();
			try {
				if(done){
					System.out.println("Exiting Editor");
					break;
				}
				change(); 
				save();
				try {
					Thread.sleep(50);
				}catch(Exception e) {
					System.out.println("There is an exception in Editor: "+e);
				}
			}finally {
				lock.unlock();
			}
		}
	}

}

