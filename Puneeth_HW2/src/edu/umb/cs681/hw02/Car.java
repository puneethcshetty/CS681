package edu.umb.cs681.hw02;

import java.util.ArrayList;

public class Car {

	private int price;
	private String name;	
	
	public Car(String name,int price) {
		this.price=price;
		this.name=name;
	}

	public int getPrice(){
		return this.price;
	}

	public String getName() {
		return this.name;
	}

	//main method
	public static void main(String args[]) {

		Car tesla=new Car("X",50000);
		Car honda=new Car("Civic",10000);
		Car kia=new Car("Kia",7000);
		ArrayList<Car> cars=new ArrayList<Car>();

		cars.add(tesla);
		cars.add(honda);
		cars.add(kia);
		
		//minPrice of cars
		Integer minprice=cars.stream().map((Car car)-> car.getPrice()).reduce(0, (result, carPrice)->{
			if(result==0) 
				return carPrice;
			else if(carPrice < result) 
				return carPrice; 
			return result;});

		//maxPrice of cars
		Integer maxprice=cars.stream().map((Car car) -> car.getPrice()).reduce(0,(result, carPrice)->{
			if(result==0)
				return carPrice;
			else if(carPrice > result)
				return carPrice;
			return result;});

		//count of cars
		Integer count=cars.stream().map((Car car) -> car.getPrice()).reduce(0, (result, carPrice)->{
			return ++result;});

		System.out.println("Total Count: " + count );
		System.out.println("Min-Price: " + minprice);
		System.out.println("Max-Price: " + maxprice);
	}
}
