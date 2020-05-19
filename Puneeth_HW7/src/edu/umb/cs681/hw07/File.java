package edu.umb.cs681.hw07;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class File {

	protected boolean changed=false;
	StringBuilder sb=new StringBuilder("Initial content of file");
	ReentrantLock lock = new ReentrantLock();

	public void change() {
		lock.lock();
		try {
			System.out.println("===============================================");
			System.out.println("----FILE BEFORE SAVE----"+"\n"+sb);
			sb.append("\nAdding through Change method into file");
			changed = true;
			System.out.println("----FILE AFTER SAVE----"+"\n"+sb);
			System.out.println("===============================================");
		}finally {
			lock.unlock();
		}

	}

	public void save() {
		lock.lock();
		try {
			if(!changed) {
				return;
			}
			else {
				System.out.println("File saved at: "+LocalDateTime.now());
				changed = false;
			}
		}finally {
			lock.unlock();
		}
	}

	public static void main(String args[]) {

		Editor editor = new Editor();
		AutoSaver autoSaver = new AutoSaver();

		Thread editThread = new Thread(editor);
		Thread autoSaverThread = new Thread(autoSaver);

		editThread.start();
		autoSaverThread.start();
		try {
			Thread.sleep(200);
		}catch(Exception e){
			System.out.println("Exception in main thread: "+e);
		}

		editor.setDone();
		autoSaver.setDone();

	}

}
