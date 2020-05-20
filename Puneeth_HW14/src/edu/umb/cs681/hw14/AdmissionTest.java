package edu.umb.cs681.hw14;

public class AdmissionTest {
	public static void main(String[] args) {
		EntranceHandler enHandler = new EntranceHandler();
		Thread entry = new Thread(enHandler);
		entry.start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Thread Interrupted "+e.getMessage());
		}
		new Thread(new MonitorHandler()).start();
		ExitHandler exHandler = new ExitHandler();
		Thread exit = new Thread(exHandler);
		exit.start();

		try {
			Thread.sleep(3000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		enHandler.setDone();entry.interrupt();
		exHandler.setDone();exit.interrupt();

		try {
			entry.join();
			exit.join();
		} catch (InterruptedException e) {
			System.out.println("Thread Interrupted while joining "+e.getMessage());
		}

		new Thread(new MonitorHandler()).start();
	}
}
