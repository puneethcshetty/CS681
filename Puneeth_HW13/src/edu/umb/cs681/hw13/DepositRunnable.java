package edu.umb.cs681.hw13;

public class DepositRunnable implements Runnable {
	volatile boolean done = false;
	private BankAccount account;

	DepositRunnable(BankAccount account){
		this.account = account;
	}

	public void setDone() {
		done = true;
	}

	public void run() {
		for(int i = 0; i < 10; i++){
			if(done)
				break;
			account.deposit(100);
			try {
				Thread.sleep(2);
			}catch(InterruptedException e) {
				System.out.println(e.toString());
				continue;
			}
		}
	}
}
