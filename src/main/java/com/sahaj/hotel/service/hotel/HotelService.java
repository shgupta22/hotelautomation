package com.sahaj.hotel.service.hotel;

import com.sahaj.hotel.exception.InvalidMotionRequest;
import com.sahaj.hotel.model.hotel.Hotel;
import com.sahaj.hotel.model.request.MotionEvent;

public interface HotelService {
    void printHotelState(Hotel hotel);
    Hotel handleMotionEvent(MotionEvent motionEvent) throws InvalidMotionRequest;
}
