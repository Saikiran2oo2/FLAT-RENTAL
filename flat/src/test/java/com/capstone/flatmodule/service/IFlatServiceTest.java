package com.capstone.flatmodule.service;

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

import com.capstone.flatmodule.entity.Flat;
import com.capstone.flatmodule.entity.FlatAddress;
import com.capstone.flatmodule.exception.FlatNotFoundException;
import com.capstone.flatmodule.repository.FlatRepository;

@SpringBootTest(properties = "eureka.client.enabled=false")
public class IFlatServiceTest {
	@InjectMocks
	private IFlatServiceImpl flatService;

	@Mock
	private FlatRepository flatRepository;

	@Test
	public void testDeleteFlat() {
		Flat flat = new Flat();
		FlatAddress flatAddress = new FlatAddress();
		flat.setFlatId(100);
		flat.setCost(8000);
		flat.setAvailability("yes");
		flatAddress.setId(1);
		flatAddress.setHouseNo(10);
		flatAddress.setCity("kgm");
		flatAddress.setCountry("India");
		flatAddress.setPin(533210);
		flatAddress.setState("AP");
		flatAddress.setStreet("jrg");

		when(flatRepository.findById(100)).thenReturn(Optional.of(flat));

		doNothing().when(flatRepository).delete(flat);

		flatService.deleteFlat(100);

		verify(flatRepository, times(1)).findById(100);
		verify(flatRepository, times(1)).delete(flat);
	}

	@Test
	public void testViewFlatById() {
		Flat flat = new Flat();
		FlatAddress flatAddress = new FlatAddress();
		flat.setFlatId(200);
		flat.setCost(8000);
		flat.setAvailability("yes");
		flatAddress.setId(1);
		flatAddress.setHouseNo(10);
		flatAddress.setCity("kgm");
		flatAddress.setCountry("India");
		flatAddress.setPin(533210);
		flatAddress.setState("AP");
		flatAddress.setStreet("jrg");

		when(flatRepository.findById(200)).thenReturn(Optional.of(flat));
		Flat actualObj = flatService.viewFlatById(200);
		assertEquals("yes", actualObj.getAvailability());
	}

	@Test
	public void testViewAllFlats() {
		Flat flat = new Flat();
		FlatAddress flatAddress = new FlatAddress();
		flat.setFlatId(100);
		flat.setCost(6000);
		flat.setAvailability("yes");
		flatAddress.setId(1);
		flatAddress.setHouseNo(10);
		flatAddress.setCity("kgm");
		flatAddress.setCountry("India");
		flatAddress.setPin(533210);
		flatAddress.setState("AP");
		flatAddress.setStreet("jrg");

		Flat flat1 = new Flat();
		FlatAddress flatAddress1 = new FlatAddress();
		flat1.setFlatId(200);
		flat1.setCost(8000);
		flat1.setAvailability("yes");
		flatAddress1.setId(2);
		flatAddress1.setHouseNo(20);
		flatAddress1.setCity("bgm");
		flatAddress1.setCountry("India");
		flatAddress1.setPin(533212);
		flatAddress1.setState("AP");
		flatAddress1.setStreet("ndl");

		Flat flat2 = new Flat();
		FlatAddress flatAddress2 = new FlatAddress();
		flat2.setFlatId(300);
		flat2.setCost(9000);
		flat2.setAvailability("no");
		flatAddress2.setId(3);
		flatAddress2.setHouseNo(30);
		flatAddress2.setCity("knp");
		flatAddress2.setCountry("India");
		flatAddress2.setPin(533213);
		flatAddress2.setState("AP");
		flatAddress2.setStreet("dpa");

		List<Flat> flats = new ArrayList<>();
		flats.add(flat);
		flats.add(flat1);
		flats.add(flat2);

		when(flatRepository.findAll()).thenReturn(flats);

		List<Flat> flatList = flatService.viewAllFlats();
		assertEquals(3, flatList.size());
	}

	@Test
	public void testAddFlat() {

		Flat flat = new Flat();
		FlatAddress flatAddress = new FlatAddress();
		flat.setFlatId(100);
		flat.setCost(6000);
		flat.setAvailability("yes");
		flatAddress.setId(1);
		flatAddress.setHouseNo(10);
		flatAddress.setCity("dbc");
		flatAddress.setCountry("India");
		flatAddress.setPin(533210);
		flatAddress.setState("AP");
		flatAddress.setStreet("tpg");
		flat.setFlatAddress(flatAddress);

		when(flatRepository.save(flat)).thenReturn(flat);

		Flat savedFlat = flatService.saveFlat(flat);

		verify(flatRepository, times(1)).save(flat);

		assertNotNull(savedFlat);

		assertEquals(flat.getAvailability(), savedFlat.getAvailability());
		assertEquals(flat.getCost(), savedFlat.getCost());
		assertEquals(flat.getFlatId(), savedFlat.getFlatId());
		assertEquals(flat.getFlatAddress().getHouseNo(), savedFlat.getFlatAddress().getHouseNo());
		assertEquals(flat.getFlatAddress().getCity(), savedFlat.getFlatAddress().getCity());
		assertEquals(flat.getFlatAddress().getCountry(), savedFlat.getFlatAddress().getCountry());
		assertEquals(flat.getFlatAddress().getState(), savedFlat.getFlatAddress().getState());
		assertEquals(flat.getFlatAddress().getStreet(), savedFlat.getFlatAddress().getStreet());
		assertEquals(flat.getFlatAddress().getPin(), savedFlat.getFlatAddress().getPin());

	}

	@Test
	public void testUpdateFlat() {
		Flat flat = new Flat();
		FlatAddress flatAddress = new FlatAddress();
		flat.setFlatId(100);
		flat.setCost(6000);
		flat.setAvailability("yes");
		flatAddress.setHouseNo(10);
		flatAddress.setCity("kgm");
		flatAddress.setCountry("India");
		flatAddress.setPin(533210);
		flatAddress.setState("AP");
		flatAddress.setStreet("jrg");
		flat.setFlatAddress(flatAddress);

		when(flatRepository.findById(100)).thenReturn(Optional.of(flat));
		when(flatRepository.save(flat)).thenReturn(flat);

		flat.setCost(8000);
		flat.setAvailability("yes");
		flatAddress.setHouseNo(10);
		flatAddress.setCity("tpg");
		flatAddress.setCountry("India");
		flatAddress.setPin(533212);
		flatAddress.setState("AP");
		flatAddress.setStreet("Aku");
		flat.setFlatAddress(flatAddress);

		Flat updatedFlat = flatService.updateFlat(flat);

		verify(flatRepository, times(1)).findById(100);
		verify(flatRepository, times(1)).save(flat);

		assertEquals(flat, updatedFlat);
		assertEquals("yes", updatedFlat.getAvailability());
		assertEquals(8000, updatedFlat.getCost());
		assertEquals(flat.getFlatAddress().getHouseNo(), updatedFlat.getFlatAddress().getHouseNo());
		assertEquals(flat.getFlatAddress().getCity(), updatedFlat.getFlatAddress().getCity());
		assertEquals(flat.getFlatAddress().getCountry(), updatedFlat.getFlatAddress().getCountry());

	}

	@Test
	public void testUpdateFlatWihException() {
		Flat flat = new Flat();
		FlatAddress flatAddress = new FlatAddress();
		flat.setFlatId(100);
		flat.setCost(6000);
		flat.setAvailability("yes");
		flatAddress.setHouseNo(10);
		flatAddress.setCity("kgm");
		flatAddress.setCountry("India");
		flatAddress.setPin(533210);
		flatAddress.setState("AP");
		flatAddress.setStreet("Anu");

		when(flatRepository.findById(100)).thenReturn(Optional.empty());

		assertThrows(FlatNotFoundException.class, () -> flatService.updateFlat(flat));
		verify(flatRepository, times(1)).findById(100);
		verify(flatRepository, times(0)).save(flat);
	}

	@Test
	public void testDeleteTenantWihException() {
		Flat flat = new Flat();
		FlatAddress flatAddress = new FlatAddress();
		flat.setFlatId(100);
		flat.setCost(6000);
		flat.setAvailability("yes");
		flatAddress.setHouseNo(10);
		flatAddress.setCity("kgm");
		flatAddress.setCountry("India");
		flatAddress.setPin(533210);
		flatAddress.setState("AP");
		flatAddress.setStreet("jrg");

		when(flatRepository.findById(100)).thenThrow(new FlatNotFoundException("Tenant is not existing with id:100"));

		assertThrows(FlatNotFoundException.class, () -> flatService.deleteFlat(100));

		verify(flatRepository, times(0)).delete(flat);
	}

}
