package com.capstone.tenantmodule.exception;
@SuppressWarnings("serial")
public class TenantNotFoundException extends RuntimeException {
	
	public TenantNotFoundException() {
		
	}
	
	public TenantNotFoundException(String msg) {
		super(msg);
	}

}
