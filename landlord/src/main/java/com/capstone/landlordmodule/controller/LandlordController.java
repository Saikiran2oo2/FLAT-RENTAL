package com.capstone.landlordmodule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.landlordmodule.entity.Landlord;
import com.capstone.landlordmodule.model.Flat;
import com.capstone.landlordmodule.model.FlatBooking;
import com.capstone.landlordmodule.model.Tenant;
import com.capstone.landlordmodule.service.FlatBookingConsumer;
import com.capstone.landlordmodule.service.FlatServiceConsumer;
import com.capstone.landlordmodule.service.LandlordService;
import com.capstone.landlordmodule.service.TenantServiceConsumer;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/landlord")
public class LandlordController {
	@Autowired
	private LandlordService landlordService;
	
	@Autowired
	private FlatServiceConsumer flatConsumer;
	
	@Autowired
	private TenantServiceConsumer tenantConsumer;
	
	@Autowired
	private FlatBookingConsumer flatBookingConsumer;
	
	@GetMapping("/all")
	public List<Landlord> fetchAllLandlords() {
		List<Landlord> landlords = landlordService.viewAllLandlords();
		return landlords;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Landlord> fetchLandlordDetails(@PathVariable("id") int landlordId) {
		Landlord landlord = landlordService.viewLandlordById(landlordId);
		return new ResponseEntity<>(landlord, HttpStatus.OK);
	}
	@PostMapping("/save")
	public ResponseEntity<Landlord> addLandlord(@RequestBody Landlord landlord) {
		landlordService.saveLandlord(landlord);
		ResponseEntity<Landlord> responseEntity = new ResponseEntity<>(landlord, HttpStatus.CREATED);
		return responseEntity;
	}
	@PutMapping("/update")
	public ResponseEntity<Landlord> editLandlord(@RequestBody Landlord landlord) {
		landlordService.updateLandlord(landlord);
		ResponseEntity<Landlord> responseEntity = new ResponseEntity<>(landlord, HttpStatus.OK);
		return responseEntity;
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLandlord(@PathVariable("id") int landlordId) {
		landlordService.deleteLandlord(landlordId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}
	@PostMapping("/flat/save")
	public ResponseEntity<Flat> saveFlat(@RequestBody Flat flat) {
		flatConsumer.saveFlat(flat);
		ResponseEntity<Flat> responseEntity = new ResponseEntity<>(flat, HttpStatus.CREATED);
		return responseEntity;
	}
	@GetMapping("/tenant//viewtenantbyid/{tenantId}")
    public ResponseEntity<Tenant> viewTenantById(@PathVariable("tenantId") int tenantId) {
        Tenant tenant= tenantConsumer.viewTenantById(tenantId);
        return new ResponseEntity<>(tenant,HttpStatus.OK);
    }
	
	@PostMapping("/flatbooking/acceptbookinge/{id}")
	public ResponseEntity<FlatBooking> acceptBooking(@PathVariable("id") int bookingId) {
		flatBookingConsumer.acceptBooking(bookingId);
		ResponseEntity<FlatBooking> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}
	
	@PostMapping("/flatbooking/denybooking/{id}")
	public ResponseEntity<FlatBooking> denyBooking(@PathVariable("id") int bookingId) {
		flatBookingConsumer.denyBooking(bookingId);
		ResponseEntity<FlatBooking> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}
	
	
	
}
