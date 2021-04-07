package ezEstimatingPackageFX;

public class Customer {
	
	private String first, last, address, city, state, zip, country, homePhone, workPhone, fax;
	
	public Customer(String newFirst, String newLast, String newAddress, String newCity, String newState, String newZip, String newCountry, String newHomePhone, String newWorkPhone, String newFax) 
	{
		first = newFirst;
		last = newLast;
		address = newAddress;
		city = newCity;
		state = newState;
		zip = newZip;
		country = newCountry;
		homePhone = newHomePhone;
		workPhone = newWorkPhone;
		fax = newFax;		
	}
	
	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getZip() {
		return zip;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getHomePhone() {
		return homePhone;
	}
	
	public String getWorkPhone() {
		return workPhone;
	}
	
	public String getFax() {
		return fax;
	}
}
