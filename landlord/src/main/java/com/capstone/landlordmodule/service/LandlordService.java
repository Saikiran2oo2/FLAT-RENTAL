package com.capstone.landlordmodule.service;

import java.util.List;

import com.capstone.landlordmodule.entity.Landlord;


public interface LandlordService {
	
	Landlord saveLandlord(Landlord landlord);

	Landlord updateLandlord(Landlord landlord);

	void deleteLandlord(int landlordId);

	Landlord viewLandlordById(int landlordId);

	List<Landlord> viewAllLandlords();
	
	
}
