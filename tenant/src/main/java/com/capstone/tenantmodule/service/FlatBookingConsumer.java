package com.capstone.tenantmodule.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capstone.tenantmodule.model.FlatBooking;




@FeignClient(name = "FLATBOOKING")
public interface FlatBookingConsumer {
	
	@PostMapping("/flatbooking/requestbooking")
	FlatBooking addBooking(@RequestBody FlatBooking flatBooking);
	
	@GetMapping("/flatbooking/status/{id}")
	String viewBookingStatusbyId(@PathVariable("id") int bookingId)	;

}
