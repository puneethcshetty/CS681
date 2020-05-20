package edu.umb.cs681.hw15;

import java.util.HashMap;
import java.util.Map;

public class StockQuoteObservable extends Observable implements Runnable{
	
	Map<String, Float> map = new HashMap<String, Float>();
	
	void changeQuote(String t,float q) {
		System.out.println("Stock "+ t + " changed!!!!");
		map.put(t, q);
		setChanged();
		notifyObservers(new StockEvent(t,q));
	}
	
	public static void main(String[] args) {
		System.out.println("StockQuoteObservable Class Successfully Run!!");
	}

	public void run() {
		
	}
}
