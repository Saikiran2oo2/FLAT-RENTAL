package com.capstone.tenantmodule.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.capstone.tenantmodule.model.Flat;
@FeignClient(name="FLAT")
public interface FlatServiceConsumer {
	
	@GetMapping("/flat/save")
	List<Flat> viewAllAvailableFlats();

}
