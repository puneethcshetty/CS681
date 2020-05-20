package edu.umb.cs681.hw14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionControl {
	private static int visitorCount = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition entryCondition = lock.newCondition();
	private Condition exitCondition = lock.newCondition();

	public int countCurrentVisitors() {
		lock.lock();
		try {
			return visitorCount;
		} finally {
			lock.unlock();
		}
	}

	public void enter() {
		lock.lock();
		try {
			while (visitorCount >= 5) {
				try {
					System.out.println("Many visitors. Please wait for a while!");
					entryCondition.await();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
			visitorCount++;
			exitCondition.signalAll();
			System.out.println("After Entry new count: "+visitorCount);
		} finally {
			lock.unlock();
		}
	}

	public void exit() {
		lock.lock();
		try {
			while (visitorCount <= 0) {
				try {
					System.out.println("No Visitors. Please wait for a while!");
					exitCondition.await();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
			visitorCount--;
			entryCondition.signalAll();
			System.out.println("After exit new count: "+visitorCount);
		} finally {
			lock.unlock();
		}
	}
}
