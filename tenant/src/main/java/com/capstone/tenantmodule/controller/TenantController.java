package com.capstone.tenantmodule.controller;

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

import com.capstone.tenantmodule.entity.Tenant;
import com.capstone.tenantmodule.model.Flat;
import com.capstone.tenantmodule.service.FlatBookingConsumer;
import com.capstone.tenantmodule.service.FlatServiceConsumer;
import com.capstone.tenantmodule.service.TenantService;


@RestController
@RequestMapping("/tenant")
public class TenantController {
	
	@Autowired
    private TenantService tenantService;
	
	@Autowired
	private FlatBookingConsumer flatBookingService;
	@Autowired
	private FlatServiceConsumer flatServiceConsumer;

    @PostMapping("/save")
    public ResponseEntity<Tenant> addTenant(@RequestBody Tenant tenant) {
    	tenantService.addTenant(tenant);
		ResponseEntity<Tenant> responseEntity = new ResponseEntity<>(tenant,HttpStatus.CREATED);
		return responseEntity;
	}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable("id") int tenantId) {
    	tenantService.deleteTenantById(tenantId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}

    @GetMapping("/all")
    public List<Tenant> viewAllTenants() {
        List<Tenant> tenants=tenantService.viewListOfTenants();
        return tenants;
    }
    @GetMapping("/viewtenantbyid/{tenantId}")
	public ResponseEntity<Tenant> viewTenantById(@PathVariable("tenantId") int tenantId) {
		Tenant tenant =tenantService.viewTenantById(tenantId);
		return new ResponseEntity<>(tenant, HttpStatus.OK);
	}
    @GetMapping("/flat/all")
    public List<Flat> viewAllFlats() {
        List<Flat> flats=flatServiceConsumer.viewAllAvailableFlats();
        return flats;
    }

	@GetMapping("/flatbooking/status/{id}")
	public ResponseEntity<String> viewBookingStatus(@PathVariable("id") int bookingId) {
	    String bookingStatus = flatBookingService.viewBookingStatusbyId(bookingId);
	    return new ResponseEntity<>(bookingStatus, HttpStatus.OK);
	}
  
}