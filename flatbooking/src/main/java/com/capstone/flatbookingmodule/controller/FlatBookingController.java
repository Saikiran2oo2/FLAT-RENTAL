package com.capstone.flatbookingmodule.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.flatbookingmodule.entity.FlatBooking;
import com.capstone.flatbookingmodule.model.Flat;
import com.capstone.flatbookingmodule.model.Tenant;
import com.capstone.flatbookingmodule.service.FlatBookingService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/flatbooking")
public class FlatBookingController {

	@Autowired
	private FlatBookingService fbookingService;

	@PostMapping("/requestbooking")
	public ResponseEntity<FlatBooking> requestflatBooking(@Valid@RequestBody FlatBooking flatBooking) {

		FlatBooking newFBooking = fbookingService.placeAFlatBooking(flatBooking);
		return new ResponseEntity<>(newFBooking, HttpStatus.CREATED);
	}

	@GetMapping("/viewbookingdetails/{id}")
	public ResponseEntity<FlatBooking> fetchBookingDetails(@PathVariable("id") int bookingId) {
		FlatBooking fbooking = fbookingService.viewBookingById(bookingId);
		return new ResponseEntity<>(fbooking, HttpStatus.OK);
	}

	@GetMapping("/all")

	public List<FlatBooking> getAllBookings() {

		List<FlatBooking> fBookingList = fbookingService.viewAllBookings();
		return fBookingList;
	}

	@DeleteMapping("/{id}")

	public ResponseEntity<Void> deleteBooking(@PathVariable("id") int bookingId) {
		fbookingService.deleteBooking(bookingId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping("/acceptbooking/{id}")
	public ResponseEntity<Void> acceptBooking(@PathVariable("id") int bookingId) {
		fbookingService.acceptBooking(bookingId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/denybooking/{id}")
	public ResponseEntity<Void> denyBooking(@PathVariable("id") int bookingId) {
		fbookingService.denyBooking(bookingId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/status/{id}")
	public ResponseEntity<String> viewBookingStatus(@PathVariable("id") int bookingId) {
	    String bookingStatus = fbookingService.viewBookingStatusbyId(bookingId);
	    return new ResponseEntity<>(bookingStatus, HttpStatus.OK);
	}

}
