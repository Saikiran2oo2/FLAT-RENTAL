package com.capstone.tenantmodule.service;

import java.util.List;

import com.capstone.tenantmodule.entity.Tenant;

public interface TenantService {
	
	Tenant addTenant(Tenant tenant);

	void deleteTenantById(int tenantId);

	List<Tenant> viewListOfTenants();
	
	Tenant viewTenantById(int tenantId);
	

}
