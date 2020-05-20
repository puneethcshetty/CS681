package edu.umb.cs681.hw15;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Observable {

	private AtomicBoolean changed = new AtomicBoolean(false);
	protected LinkedList<Observer> observer = new LinkedList<Observer>();
	private static ReentrantLock lock = new ReentrantLock();

	public void addObserver(Observer o) {
		lock.lock();
		try {
			if(!observer.contains(o)) {
				observer.add(o);
			}
		}finally {
			lock.unlock();
		}
	}

	public void deleteObserver(Observer o) {
		lock.lock();
		try {
			if(observer.contains(o)) {
				observer.remove(o);
			}
		}finally {
			lock.unlock();
		}
	}

	protected int countObserver() {
		lock.lock();
		try {
			return observer.size();
		}finally {
			lock.unlock();
		}
	}

	protected void setChanged() {
		changed.set(true);
	}

	protected void clearChanged() {
		changed.set(false);
	}

	public void notifyObservers(Object obj) {
		System.out.println("----"+getClass().getSimpleName()+"----");
		if(changed.get()) {
			lock.lock();
			try {
				observer.forEach((Observer observer)->observer.update(this,obj));
			}finally {
				lock.unlock();
			}
			clearChanged();
		}
		System.out.println("\n");
	}
}
