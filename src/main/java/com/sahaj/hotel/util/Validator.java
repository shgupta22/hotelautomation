package com.sahaj.hotel.util;

import com.sahaj.hotel.exception.InvalidHotelRequest;
import com.sahaj.hotel.exception.InvalidMotionRequest;
import com.sahaj.hotel.model.request.HotelRequest;
import com.sahaj.hotel.model.request.MotionEvent;

public class Validator {
    public static void validateHotelRequest(HotelRequest hotelRequest) throws InvalidHotelRequest {
        if (hotelRequest.getNoOfFloors() <= 0) {
            throw new InvalidHotelRequest("No. of Floors must be positive numbers");
        }
        if (hotelRequest.getNoOfMainCorridor() <= 0) {
            throw new InvalidHotelRequest("No. of MainCorridor must be positive numbers");
        }
        if (hotelRequest.getNoOfSubCorridor() <= 0) {
            throw new InvalidHotelRequest("No. of SubCorridor must be positive numbers");
        }
    }

    public static void validateMotionRequest(MotionEvent motionEvent) throws InvalidMotionRequest {
        if (motionEvent.getHotel() == null) {
            throw new InvalidMotionRequest("Hotel cannot be null");
        }
        if (motionEvent.getFloorNo() <= 0) {
            throw new InvalidMotionRequest("Floor number must be a positive number");
        }
        if (motionEvent.getHotel().getFloors().size() < motionEvent.getFloorNo()) {
            throw new InvalidMotionRequest("Invalid Floor number, as Hotel has only " + motionEvent.getHotel().getFloors().size() + " Floors");
        }
        if (motionEvent.getSubCorridorNo() <= 0) {
            throw new InvalidMotionRequest("SubCorridor number must be a positive number");
        }
        if (motionEvent.getHotel().getFloors().get(0).getSubCorridors().size() < motionEvent.getSubCorridorNo()) {
            throw new InvalidMotionRequest("Invalid Sub Corridor, as Hotel has only " + motionEvent.getHotel().getFloors().get(0).getSubCorridors().size() + " SubCorridors");
        }
    }
}
