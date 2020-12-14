package model.logic;

import java.util.Date;

public class tax2 implements Comparable <tax2> {
	
	private String TripId;
	private String TaxiId;
	private Date trip_start_timestamp;
	private double trip_miles;
	private double trip_total;
	
	public tax2(String tripId) {
		super();
		TripId = tripId;
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


	@Override
	public int compareTo(tax2 arg0) {
		// TODO Auto-generated method stub
		return 0;
	}



	public Date getTrip_start_timestamp() {
		return trip_start_timestamp;
	}



	public void setTrip_start_timestamp(Date trip_start_timestamp) {
		this.trip_start_timestamp = trip_start_timestamp;
	}



	public Double getTrip_miles() {
		return trip_miles;
	}



	public void setTrip_miles(Double trip_miles) {
		this.trip_miles = trip_miles;
	}



	public Double getTrip_total() {
		return trip_total;
	}



	public void setTrip_total(Double trip_total) {
		this.trip_total = trip_total;
	}
}
