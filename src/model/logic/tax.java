package model.logic;

import java.sql.Time;
import java.util.Date;

public class tax implements Comparable <tax> {
	
	public tax(String tripId) {
		super();
		TripId = tripId;
	}

	private String TripId;
	private String TaxiId;
	private String Company;
	
	
	@Override
	public int compareTo(tax arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getTripId() {
		return TripId;
	}

	public void setTripId(String tripId) {
		TripId = tripId;
	}

	public String getTaxiId() {
		return TaxiId;
	}

	public void setTaxiId(String taxiId) {
		TaxiId = taxiId;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}


}
