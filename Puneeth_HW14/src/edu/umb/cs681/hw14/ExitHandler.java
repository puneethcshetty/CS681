package edu.umb.cs681.hw14;


public class ExitHandler implements Runnable {
	AdmissionControl control = new AdmissionControl();
	volatile boolean done = false;

	public void setDone() {
		System.out.println("ExitHandler Done!");
		done = true;
	}

	public void run() {
		while(true) {
			if(done)
				break;
			control.exit();
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				System.out.println("ExitHandler Interrupted!");
				System.out.println(e.toString());
				continue;
			}
		}
	}
}
