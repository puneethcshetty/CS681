package edu.umb.cs681.hw12;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccessCounter {
	private static ReentrantLock instanceLock= new ReentrantLock();
	private ReentrantReadWriteLock lock;
	private static AccessCounter ac = null;
	private HashMap<java.nio.file.Path, Integer> count;


	private AccessCounter() {
		count = new HashMap<java.nio.file.Path, Integer>();
		lock = new ReentrantReadWriteLock();
	}

	public static AccessCounter getInstance() {
		instanceLock.lock();
		try {
			if (ac == null) {
				ac = new AccessCounter();
			}
			return ac;
		} finally {
			instanceLock.unlock();
		}
	}

	public int getCount(Path path) {
		lock.readLock().lock();
		try {
			if (count.containsKey(path)) {
				return count.get(path);
			} else {
				return 0;
			}
		} finally {
			System.out.println(Thread.currentThread().getName()  + " getCount " + path + " count " + count.get(path));
			lock.readLock().unlock();
		}
	}

	public void increment(Path path) {
		lock.readLock().lock();
		try {
			if (count.containsKey(path)) {
				count.put(path, count.get(path) + 1);
			} else {
				count.put(path, 1);
			}
		} finally {
			System.out.println(Thread.currentThread().getName()  + " increment " + path + " count " + count.get(path));
			lock.readLock().unlock();
		}
	}
}
