package com.capstone.flatmodule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.flatmodule.entity.Flat;

public interface FlatRepository extends JpaRepository<Flat,Integer>{


	List<Flat> findByCost(float flatCost);


}
