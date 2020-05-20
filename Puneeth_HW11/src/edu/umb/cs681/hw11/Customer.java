package edu.umb.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;

public class Customer implements Runnable {
    private Address address;
    ReentrantLock lock = new ReentrantLock();

    public Customer(Address addr){
    	lock.lock();
		try {
			address = addr;
		}finally {
			lock.unlock();
		}
    }
    public void setAddress(Address addr){
    	lock.lock();
		try {
			address = addr;
		}finally {
			lock.unlock();
		}
    }

    public Address getAddress(){
    	lock.lock();
		try {
            return address;
	    }finally {
			lock.unlock();
		}
    }
    
    public void run() {
    	lock.lock();
		try {
			this.setAddress(address);
			System.out.println("CustomerDetails: "+this.getAddress());
		}finally {
			lock.unlock();
		}
    }
    
    public static void main(String args[]) {
    	
    	Customer a = new Customer(new Address("efg","Boston","MA",1234));
        Customer b = new Customer(new Address("abcd","NY","NY",5678));
        Customer c = new Customer(new Address("xyz","Boston","MA",4567));
        
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        Thread t3 = new Thread(c);
        
        t1.start(); t2.start(); t3.start();
        c.setAddress(c.getAddress().change("efg","aaa","MA",1234));
        System.out.println(c.getAddress());
        try {
        	t1.join();  t2.join(); t3.join();
        }catch(InterruptedException e) {
        	e.printStackTrace();
        }
    }
}
