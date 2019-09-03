package com.sahaj.hotel.service.floor;

import com.sahaj.hotel.model.hotel.corridor.MainCorridor;
import com.sahaj.hotel.model.hotel.corridor.SubCorridor;

import java.util.List;

public interface FloorService {
    int calculateCurrentPowerConsumption(List<MainCorridor> mainCorridors, List<SubCorridor> subCorridors);
}
