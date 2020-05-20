package edu.umb.cs681.hw17;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Observable {

	private AtomicBoolean changed = new AtomicBoolean(false);
	protected ConcurrentLinkedQueue<Observer> observer = new ConcurrentLinkedQueue<Observer>();

	public void addObserver(Observer o) {
		if(!observer.contains(o)) {
			observer.add(o);
		}
	}

	public void deleteObserver(Observer o) {
		if(observer.contains(o)) {
			observer.remove(o);
		}
	}

	protected int countObserver() {
		return observer.size();
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
			observer.forEach((Observer observer)->observer.update(this,obj));
			clearChanged();
		}
		System.out.println("\n");
	}
}
