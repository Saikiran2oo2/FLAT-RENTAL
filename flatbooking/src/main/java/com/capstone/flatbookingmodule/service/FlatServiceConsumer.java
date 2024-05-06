package com.capstone.flatbookingmodule.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.flatbookingmodule.model.Flat;



@FeignClient(name="FLAT")
public interface FlatServiceConsumer {
	
	@GetMapping("/flat/{id}")
	Flat getFlatById(@PathVariable("id") int flatNo);
	
}
