package com.capstone.flatmodule.service;

import java.util.List;

import com.capstone.flatmodule.entity.Flat;


public interface IFlatService {

	Flat saveFlat(Flat flat);

	Flat updateFlat(Flat flat);

	void deleteFlat(int flatId);

	Flat viewFlatById(int flatId);

	List<Flat> viewAllFlats();

	List<Flat> viewAllFlatsByCost(float cost);
}