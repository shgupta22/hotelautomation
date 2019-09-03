package com.sahaj.hotel.model.request;

import com.sahaj.hotel.model.hotel.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MotionEvent {
    private Hotel hotel;
    private int floorNo;
    private int subCorridorNo;
}
