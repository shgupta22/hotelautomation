package com.sahaj.hotel.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HotelRequest {
    int noOfFloors;
    int noOfMainCorridor;
    int noOfSubCorridor;
}
