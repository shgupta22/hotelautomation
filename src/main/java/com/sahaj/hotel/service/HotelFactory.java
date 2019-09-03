package com.sahaj.hotel.service;

import com.sahaj.hotel.exception.InvalidHotelRequest;
import com.sahaj.hotel.model.hotel.Floor;
import com.sahaj.hotel.model.hotel.Hotel;
import com.sahaj.hotel.model.request.HotelRequest;
import com.sahaj.hotel.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class HotelFactory {

    public Hotel getHotel(HotelRequest hotelRequest) throws InvalidHotelRequest {
        Validator.validateHotelRequest(hotelRequest);
        List<Floor> floors = new ArrayList<>();
        for (int i = 0; i < hotelRequest.getNoOfFloors(); i++) {
            floors.add(new Floor(i+1, hotelRequest.getNoOfMainCorridor(), hotelRequest.getNoOfSubCorridor()));
        }
        return new Hotel(floors);
    }
}
