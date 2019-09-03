package com.sahaj.hotel.model.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class Hotel {
    List<Floor> floors;
}
