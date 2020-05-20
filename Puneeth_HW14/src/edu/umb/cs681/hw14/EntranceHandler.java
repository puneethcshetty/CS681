package edu.umb.cs681.hw14;

public class EntranceHandler implements Runnable  {

	AdmissionControl control = new AdmissionControl();
	volatile boolean done = false;

	public void setDone() {
		System.out.println("EntranceHandler Done!");
		done = true;
	}

	public void run() {
		while(true) {
			if(done)
				break;
			control.enter();
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				System.out.println("EntranceHandler Interrupted!");
				System.out.println(e.toString());
				continue;
			}

		}
	}
}
