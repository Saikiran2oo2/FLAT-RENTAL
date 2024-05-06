package com.capstone.landlordmodule.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.capstone.landlordmodule.entity.Landlord;
import com.capstone.landlordmodule.exception.LandlordNotFoundException;
import com.capstone.landlordmodule.repository.LandlordRepository;



@SpringBootTest(properties = "eureka.client.enabled=false")
public class ILandlordServiceTest {
	@InjectMocks
	private LandlordServiceImpl landlordService;
	@Mock
	private LandlordRepository landlordRepository;
	@Test
	public void testviewLandlord() {
		Landlord landlord=new Landlord();
		landlord.setLandlordId(111);
		landlord.setLandlordName("Saikiran");
		landlord.setLandlordAge(22);
		landlord.setMobileNo("6309490829");
		
		when(landlordRepository.findById(111)).thenReturn(Optional.of(landlord));
		Landlord actualObj=landlordService.viewLandlordById(111);
		assertEquals("Saikiran",actualObj.getLandlordName());
	}
	@Test
	public void testViewLandlordException() {
		
		when(landlordRepository.findById(200)).thenThrow(new LandlordNotFoundException("Landlord is not existing with id:200"));
		assertThrows(LandlordNotFoundException.class, () -> landlordService.viewLandlordById(200));
	}
	@Test
	public void testViewAllLandlords() {
		Landlord landlord=new Landlord();
		landlord.setLandlordId(111);
		landlord.setLandlordName("6309490829");
		landlord.setLandlordAge(21);
		landlord.setMobileNo("6309490829");
		
		Landlord landlord1=new Landlord();
		landlord1.setLandlordId(222);
		landlord1.setLandlordName("vara");
		landlord1.setLandlordAge(23);
		landlord1.setMobileNo("8096082927");
		
		List<Landlord> landlords=new ArrayList<>();	
		landlords.add(landlord);
		landlords.add(landlord1);
		when(landlordRepository.findAll()).thenReturn(landlords);
		List<Landlord> landlordList=landlordService.viewAllLandlords();
		assertEquals(2,landlordList.size());
		
	}
	@Test
	void testDeleteLandlord() {
		Landlord landlord=new Landlord();
		landlord.setLandlordId(111);
		landlord.setLandlordName("Saikiran");
		landlord.setLandlordAge(22);
		landlord.setMobileNo("6309490829");

		when(landlordRepository.findById(111)).thenReturn(Optional.of(landlord));
		doNothing().when(landlordRepository).delete(landlord);

		landlordService.deleteLandlord(111);;
		verify(landlordRepository, times(1)).findById(111);
		verify(landlordRepository, times(1)).delete(landlord);
		
	}
	@Test
	void testDeleteLandlordException() {
		Landlord landlord=new Landlord();
		landlord.setLandlordId(111);
		landlord.setLandlordName("Saikiran");
		landlord.setLandlordAge(22);
		landlord.setMobileNo("6309490829");

		when(landlordRepository.findById(111)).thenThrow(new LandlordNotFoundException("landlord is not existing with id:111"));
		assertThrows(LandlordNotFoundException.class, () -> landlordService.deleteLandlord(111));
		verify(landlordRepository, times(0)).delete(landlord);
		
	}
	@Test
	public void testAddLandlord() {
	    Landlord landlord = new Landlord();
	    landlord.setLandlordName("Saikiran");
	    landlord.setLandlordAge(22);
	    landlord.setMobileNo("6309490829");

	    when(landlordRepository.save(landlord)).thenReturn(landlord);

	    Landlord addedLandlord = landlordService.saveLandlord(landlord);

	    verify(landlordRepository, times(1)).save(landlord);
	    assertEquals(landlord, addedLandlord);
	    assertEquals("Saikiran", addedLandlord.getLandlordName());
	    assertEquals(22, addedLandlord.getLandlordAge());
	    assertEquals("6309490829", addedLandlord.getMobileNo());
	}
	@Test
	public void testUpdateLandlord() {
	    Landlord landlord = new Landlord();
	    landlord.setLandlordId(1);
	    landlord.setLandlordName("Saikiran");
	    landlord.setLandlordAge(22);
	    landlord.setMobileNo("6309490829");

	    when(landlordRepository.findById(1)).thenReturn(Optional.of(landlord));
	    when(landlordRepository.save(landlord)).thenReturn(landlord);

	    landlord.setLandlordName("Saikiran");
	    landlord.setLandlordAge(24);
	    landlord.setMobileNo("6309490829");

	    Landlord updatedLandlord = landlordService.updateLandlord(landlord);

	    verify(landlordRepository, times(1)).findById(1);
	    verify(landlordRepository, times(1)).save(landlord);
	    assertEquals(landlord, updatedLandlord);
	    assertEquals("Saikiran", updatedLandlord.getLandlordName());
	    assertEquals(24, updatedLandlord.getLandlordAge());
	    assertEquals("6309490829", updatedLandlord.getMobileNo());
	}
	@Test
	public void testUpdateLandlordWithException() {
	    Landlord landlord = new Landlord();
	    landlord.setLandlordId(1);
	    landlord.setLandlordName("daddy");
	    landlord.setLandlordAge(21);
	    landlord.setMobileNo("6309710881");

	    when(landlordRepository.findById(1)).thenReturn(Optional.empty());

	    assertThrows(LandlordNotFoundException.class, () -> landlordService.updateLandlord(landlord));
	    verify(landlordRepository, times(1)).findById(1);
	    verify(landlordRepository, times(0)).save(landlord);
	}
	
 
}