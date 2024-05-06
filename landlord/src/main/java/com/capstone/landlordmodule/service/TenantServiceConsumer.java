package com.capstone.landlordmodule.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.landlordmodule.model.Tenant;



@FeignClient(name = "TENANT")
public interface TenantServiceConsumer {
	
	@GetMapping("/tenant/viewtenantbyid/{tenantId}")
    Tenant viewTenantById(@PathVariable("tenantId") int tenantId);

}
