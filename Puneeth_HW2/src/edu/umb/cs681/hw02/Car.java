package edu.umb.cs681.hw02;

import java.util.ArrayList;

public class Car {

	private int price;
	private String name;
	private ArrayList<Car> car;

	public Car() {
		this.car=new ArrayList<>();
	}	
	
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

	public void setCar(ArrayList<Car> car) {
		this.car=car;
	}

	public ArrayList<Car> getCar() {
		return car;
	}

	//main method
	public static void main(String args[]) {

		Car Tesla=new Car("X",50000);
		Car Honda=new Car("Civic",10000);
		Car Kia=new Car("Kia",7000);
		ArrayList<Car> cars=new ArrayList<Car>();

		Car c=new Car();
		cars.add(Tesla);
		cars.add(Honda);
		cars.add(Kia);
		c.setCar(cars);
		
		//minPrice of cars
		Integer minprice=cars.stream().map((Car car)-> car.getPrice()).reduce(0, (result, carPrice)->{
			if(result==0) 
				return carPrice;
			else if(carPrice < result) 
				return carPrice;
			else 
				return result;}
				);
		
		//maxPrice of cars
		Integer maxprice=cars.stream().map((Car car) -> car.getPrice()).reduce(0,(result, carPrice)->{
			if(result==0)
				return carPrice;
			else if(carPrice > result)
				return carPrice;
			else
				return result;
		});

		//count of cars
		Integer count=cars.stream().map((Car car) -> car.getPrice()).reduce(0, (result, carPrice)->{
			return ++result;});

		System.out.println("Total Count: " + count );
		System.out.println("Min-Price: " + minprice);
		System.out.println("Max-Price: " + maxprice);
	}
}
