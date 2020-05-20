package edu.umb.cs681.hw13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount2 implements BankAccount{
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();
	
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (d): current balance: " + balance);
			while(balance >= 300){
				System.out.println(Thread.currentThread().getId() + 
						" (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().getId() + 
					" (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		}
		catch (InterruptedException e){
			System.out.println(e.toString());
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (w): current balance: " + balance);
			while(balance <= 0){
				System.out.println(Thread.currentThread().getId() + 
						" (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getId() + 
					" (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		}
		catch (InterruptedException e){
			System.out.println(e.toString());
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public static void main(String[] args){
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
		DepositRunnable dr1 = new DepositRunnable(bankAccount);
		WithdrawRunnable wr1 = new WithdrawRunnable(bankAccount);
		DepositRunnable dr2 = new DepositRunnable(bankAccount);
		WithdrawRunnable wr2 = new WithdrawRunnable(bankAccount);
		DepositRunnable dr3 = new DepositRunnable(bankAccount);
		WithdrawRunnable wr3 = new WithdrawRunnable(bankAccount);
		DepositRunnable dr4 = new DepositRunnable(bankAccount);
		WithdrawRunnable wr4 = new WithdrawRunnable(bankAccount);
		Thread t1 = new Thread(dr1);
		Thread t2 = new Thread(wr1);
		Thread t3 = new Thread(dr2);
		Thread t4 = new Thread(wr2);
		Thread t5 = new Thread(dr3);
		Thread t6 = new Thread(wr3);
		Thread t7 = new Thread(dr4);
		Thread t8 = new Thread(wr4);
		t1.start();t2.start();t3.start();t4.start();
		t5.start();t6.start();t7.start();t8.start();
		try {
			Thread.sleep(10);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		dr1.setDone();t1.interrupt();
		dr2.setDone();t3.interrupt();
		dr3.setDone();t5.interrupt();
		dr4.setDone();t7.interrupt();
		wr1.setDone();t2.interrupt();
		wr2.setDone();t4.interrupt();
		wr3.setDone();t6.interrupt();
		wr4.setDone();t8.interrupt();
		try {
			t1.join();t2.join();t3.join();t4.join();
			t5.join();t6.join();t7.join();t8.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
