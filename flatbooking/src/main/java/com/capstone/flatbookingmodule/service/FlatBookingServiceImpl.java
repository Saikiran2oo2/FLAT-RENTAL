package com.capstone.flatbookingmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.flatbookingmodule.entity.FlatBooking;
import com.capstone.flatbookingmodule.exception.FlatBookingNotFoundException;
import com.capstone.flatbookingmodule.model.Flat;
import com.capstone.flatbookingmodule.model.Tenant;
import com.capstone.flatbookingmodule.repository.FlatBookingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class FlatBookingServiceImpl implements FlatBookingService {

    @Autowired
    private FlatBookingRepository flatBookingRepository;

    @Autowired
    private FlatServiceConsumer flatService;

    @Autowired
    private TenantServiceConsumer tenantService;

    @Override
    public FlatBooking placeAFlatBooking(FlatBooking flatBooking) {
        flatBooking.setBookingDate(LocalDate.now());
        flatBooking.setBookingStatus("pending");
        flatBooking.setBookingFromDate(LocalDate.of(2024, 05, 06));
        flatBooking.setBookingToDate(LocalDate.of(2025, 07, 17));

        flatBookingRepository.save(flatBooking);
        return flatBooking;
    }

    @Override
    public FlatBooking viewBookingById(int bookingId) {
        Optional<FlatBooking> optionalBooking = flatBookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new FlatBookingNotFoundException("Flat booking does not exist with id" + bookingId);
        }

        FlatBooking fbooking = optionalBooking.get();
        return fbooking;
    }

    @Override
    public List<FlatBooking> viewAllBookings() {
        List<FlatBooking> fBookingList = flatBookingRepository.findAll();
        return fBookingList;
    }

    @Override
    public void deleteBooking(int bookingId) {
        Optional<FlatBooking> optionalBooking = flatBookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new FlatBookingNotFoundException("Flat booking does not exist with id" + bookingId);
        }
        FlatBooking flatBooking = optionalBooking.get();
        flatBookingRepository.delete(flatBooking);
    }
    
    @Override
    public void acceptBooking(int bookingId) {
        Optional<FlatBooking> optionalBooking = flatBookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new FlatBookingNotFoundException("Flat booking does not exist with id" + bookingId);
        }
        FlatBooking flatBooking = optionalBooking.get();
        flatBooking.setBookingStatus("YOUR FLAT BOOKING IS ACCEPCTED");
        flatBookingRepository.save(flatBooking);
    }

    @Override
    public void denyBooking(int bookingId) {
        Optional<FlatBooking> optionalBooking = flatBookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new FlatBookingNotFoundException("Flat booking does not exist with id" + bookingId);
        }
        FlatBooking flatBooking = optionalBooking.get();
        flatBooking.setBookingStatus("YOUR FLAT BOOKING IS DENIED");
        flatBookingRepository.save(flatBooking);
        
    }


    

    @Override
	public String viewBookingStatusbyId(int bookingId) {
		 Optional<FlatBooking> optionalBooking = flatBookingRepository.findById(bookingId);
		    if (optionalBooking.isEmpty()) {
		        throw new FlatBookingNotFoundException("Flat booking does not exist with id" + bookingId);
		    }
		    FlatBooking flatBooking = optionalBooking.get();
		    return flatBooking.getBookingStatus();
	}
}