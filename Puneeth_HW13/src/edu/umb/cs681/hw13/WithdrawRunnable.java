package edu.umb.cs681.hw13;

public class WithdrawRunnable implements Runnable{
	private BankAccount account;
	volatile boolean done = false;

	WithdrawRunnable(BankAccount account){
		this.account = account;
	}

	public void setDone() {
		done = true;
	}

	public void run() {
		for(int i = 0; i < 10; i++){
			if(done)
				break;
			account.withdraw(100);
			try {
				Thread.sleep(2);
			}catch(InterruptedException e) {
				System.out.println(e.toString());
				continue;
			}
		}
	}
}
