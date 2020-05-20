package edu.umb.cs681.hw11;

import java.util.Objects;

public final class Address {
	private final String street, city, state;
	private final int zipcode;

	public Address(String street, String city, String state, int zipcode) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public Address change(String street, String city, String state, int zipcode){
		System.out.println("Inside Change function");
		return new Address(street, city, state, zipcode); 
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return zipcode == address.zipcode &&
                Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(street, city, state, zipcode);
    }

	public String toString() {
		return street + ", " + city + ", " + state + " - " + zipcode;
	}
}
