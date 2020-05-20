package edu.umb.cs681.hw14;

public class MonitorHandler implements Runnable  {

	AdmissionControl control = new AdmissionControl();
	public void run() {
		System.out.println("Current total visitors: "+control.countCurrentVisitors());
	}
}
