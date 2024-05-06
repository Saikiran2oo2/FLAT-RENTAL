package com.capstone.tenantmodule.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tenant_tbl")
public class Tenant {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int tenantId;
	private String tenantName;
	private int tenantAge;
	@OneToOne(cascade = CascadeType.ALL)
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
