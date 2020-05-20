package edu.umb.cs681.hw16;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {

	private ReentrantLock lock;
	private boolean done;
	private static ArrayList<Path> arrayPaths = new ArrayList<Path>();

	public RequestHandler() {
		lock = new ReentrantLock();
	}

	public void setDone() {
		lock.lock();
		try {
			done = true;
		} finally {
			lock.unlock();
		}
	}

	public void run() {
		Random r = new Random();
		while (true) {
			lock.lock();
			try {
				if(done)
					break;
			}
			finally {
				lock.unlock();
			}
			Path filePath = arrayPaths.get(r.nextInt(arrayPaths.size()));
			AccessCounter.getInstance().increment(filePath);
			AccessCounter.getInstance().getCount(filePath);
			try {
				Thread.sleep(3000);
			}	
			catch(InterruptedException e){
				continue;
			} 
		}
	}

	public static void main(String[] args) {
		ArrayList<RequestHandler> requests = new ArrayList<>();
		ArrayList<Thread> threads = new ArrayList<>();
		arrayPaths.add(Paths.get("a.html"));
		arrayPaths.add(Paths.get("b.html"));
		arrayPaths.add(Paths.get("c.html"));
		arrayPaths.add(Paths.get("d.html"));
		arrayPaths.add(Paths.get("e.html"));
		for(int i = 0; i < 15; i++) {
			RequestHandler request = new RequestHandler();
			Thread t = new Thread(request);
			threads.add(t);
			requests.add(request);
			t.start();
		}
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		Thread t = null;
		RequestHandler request = null;
		for(int i = 0; i < 15; i++) {
			t = threads.get(i);
			request = requests.get(i);
			try {
				request.setDone();
				t.interrupt();
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
