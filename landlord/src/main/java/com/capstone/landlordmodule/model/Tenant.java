package com.capstone.landlordmodule.model;

public class Tenant {
	private int tenantId;
	private String tenantName;
	private int tenantAge;

	private TenantAddress tenantAddress;

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public int getTenantAge() {
		return tenantAge;
	}

	public void setTenantAge(int tenantAge) {
		this.tenantAge = tenantAge;
	}

	public TenantAddress getTenantAddress() {
		return tenantAddress;
	}

	public void setTenantAddress(TenantAddress tenantAddress) {
		this.tenantAddress = tenantAddress;
	}
	
	

}
