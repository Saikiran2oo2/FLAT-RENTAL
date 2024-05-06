package com.capstone.tenantmodule.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.tenantmodule.entity.Tenant;
import com.capstone.tenantmodule.entity.TenantAddress;
import com.capstone.tenantmodule.exception.TenantNotFoundException;
import com.capstone.tenantmodule.repository.TenantRepository;

@SpringBootTest
public class ITenantServiceTest {
	@InjectMocks
	private ITenantServiceImpl tenantService;
	@Mock
	private TenantRepository tenantRepository;
	
	@Test
	public void testAddTenant() {
		Tenant tenant = new Tenant();
		TenantAddress tenantAddress = new TenantAddress();
		tenant.setTenantId(200);
		tenant.setTenantName("raja");
		tenant.setTenantAge(21);
		tenantAddress.setId(2);
		tenantAddress.setHouseNo(20);
		tenantAddress.setCity("surampalem");
		tenantAddress.setCountry("India");
		tenantAddress.setPin(533222);
		tenantAddress.setState("ap");
		tenantAddress.setStreet("yalla");
		tenant.setTenantAddress(tenantAddress);

		when(tenantRepository.save(tenant)).thenReturn(tenant);

		Tenant savedTenant = tenantService.addTenant(tenant);

		verify(tenantRepository, times(1)).save(tenant);

		assertNotNull(savedTenant);

		assertEquals(tenant.getTenantName(), savedTenant.getTenantName());
		assertEquals(tenant.getTenantAge(), savedTenant.getTenantAge());
		assertEquals(tenant.getTenantAddress().getHouseNo(), savedTenant.getTenantAddress().getHouseNo());
		assertEquals(tenant.getTenantAddress().getCity(), savedTenant.getTenantAddress().getCity());
		assertEquals(tenant.getTenantAddress().getCountry(), savedTenant.getTenantAddress().getCountry());
	}

	@Test
	public void testDeleteTenant() {
		Tenant tenant = new Tenant();
		TenantAddress tenantAddress = new TenantAddress();
		tenant.setTenantId(100);
		tenant.setTenantName("kohlie");
		tenant.setTenantAge(21);
		tenantAddress.setId(1);
		tenantAddress.setHouseNo(10);
		tenantAddress.setCity("amp");
		tenantAddress.setCountry("India");
		tenantAddress.setPin(533210);
		tenantAddress.setState("AP");
		tenantAddress.setStreet("Athili");

		when(tenantRepository.findById(100)).thenReturn(Optional.of(tenant));

		doNothing().when(tenantRepository).delete(tenant);

		tenantService.deleteTenantById(100);

		verify(tenantRepository, times(1)).findById(100);
		verify(tenantRepository, times(1)).delete(tenant);

	}

	@Test
	public void testDeleteTenantWihException() {
		Tenant tenant = new Tenant();
		TenantAddress tenantAddress = new TenantAddress();
		tenant.setTenantId(300);
		tenant.setTenantName("kanna");
		tenant.setTenantAge(22);
		tenantAddress.setId(3);
		tenantAddress.setHouseNo(30);
		tenantAddress.setCity("gnp");
		tenantAddress.setCountry("India");
		tenantAddress.setPin(533233);
		tenantAddress.setState("ap");
		tenantAddress.setStreet("kgp");

		when(tenantRepository.findById(300))
				.thenThrow(new TenantNotFoundException("Tenant is not existing with id:300"));

		assertThrows(TenantNotFoundException.class, () -> tenantService.deleteTenantById(300));

		verify(tenantRepository, times(0)).delete(tenant);
	}

	@Test
	public void testViewAllTenants() {
		Tenant tenant = new Tenant();
		TenantAddress tenantAddress = new TenantAddress();
		tenant.setTenantId(300);
		tenant.setTenantName("kanna");
		tenant.setTenantAge(22);
		tenantAddress.setId(3);
		tenantAddress.setHouseNo(30);
		tenantAddress.setCity("gnp");
		tenantAddress.setCountry("India");
		tenantAddress.setPin(533233);
		tenantAddress.setState("ap");
		tenantAddress.setStreet("kgp");

		Tenant tenant1 = new Tenant();
		TenantAddress tenantAddress1 = new TenantAddress();
		tenant1.setTenantId(400);
		tenant1.setTenantName("naidu");
		tenant1.setTenantAge(21);
		tenantAddress1.setId(4);
		tenantAddress1.setHouseNo(40);
		tenantAddress1.setCity("Gannavaram");
		tenantAddress1.setCountry("India");
		tenantAddress1.setPin(533244);
		tenantAddress1.setState("tn");
		tenantAddress1.setStreet("yvp");

		Tenant tenant2 = new Tenant();
		TenantAddress tenantAddress2 = new TenantAddress();
		tenant2.setTenantId(500);
		tenant2.setTenantName("uma");
		tenant2.setTenantAge(25);
		tenantAddress2.setId(5);
		tenantAddress2.setHouseNo(50);
		tenantAddress2.setCity("avl");
		tenantAddress2.setCountry("India");
		tenantAddress2.setPin(533253);
		tenantAddress2.setState("Ap");
		tenantAddress2.setStreet("vvp");

		List<Tenant> tenants = new ArrayList<>();
		tenants.add(tenant);
		tenants.add(tenant1);
		tenants.add(tenant2);

		when(tenantRepository.findAll()).thenReturn(tenants);

		List<Tenant> tenantList = tenantService.viewListOfTenants();
		assertEquals(3, tenantList.size());

	}

}