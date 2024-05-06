package com.capstone.flatbookingmodule.service;

import java.util.List;

import com.capstone.flatbookingmodule.entity.FlatBooking;
import com.capstone.flatbookingmodule.model.Flat;
import com.capstone.flatbookingmodule.model.Tenant;

public interface FlatBookingService {

	FlatBooking placeAFlatBooking(FlatBooking flatBooking);
    
	FlatBooking viewBookingById(int bookingId);
    
    List<FlatBooking> viewAllBookings();
    
    void deleteBooking(int bookingId);
     
	void acceptBooking(int bookingId);

    void denyBooking(int bookingId);
    
    String viewBookingStatusbyId(int bookingId);
	
}
