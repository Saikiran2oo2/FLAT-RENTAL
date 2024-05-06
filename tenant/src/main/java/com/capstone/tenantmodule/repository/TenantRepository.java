package com.capstone.tenantmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.tenantmodule.entity.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {
   
}