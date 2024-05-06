package com.capstone.flatbookingmodule.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.flatbookingmodule.model.Tenant;



@FeignClient(name="TENANT")
public interface TenantServiceConsumer {

	@GetMapping("/tenant/viewtenant/{tenantId}")
	Tenant getTenantDetails(@PathVariable("tenantId") int tenantId);
	
	//Tenant viewTenantById(@PathVariable("tenantId")int tenantId);
	
}
