package com.sahaj.hotel.service.floor;

import com.sahaj.hotel.model.enums.EquipmentState;
import com.sahaj.hotel.model.hotel.corridor.MainCorridor;
import com.sahaj.hotel.model.hotel.corridor.SubCorridor;
import com.sahaj.hotel.service.floor.FloorService;

import java.util.List;

public class FloorServiceImpl implements FloorService {

    @Override
    public int calculateCurrentPowerConsumption(List<MainCorridor> mainCorridors, List<SubCorridor> subCorridors) {
        int currentPowerConsumption = 0;
        for (MainCorridor mainCorridor : mainCorridors) {
            currentPowerConsumption += mainCorridor.getAc().getPowerConsumption() + mainCorridor.getLight().getPowerConsumption();
        }

        for (SubCorridor subCorridor : subCorridors) {
            if (subCorridor.getAc().getState() == EquipmentState.ON) {
                currentPowerConsumption += subCorridor.getAc().getPowerConsumption();
            }
            if (subCorridor.getLight().getState() == EquipmentState.ON) {
                currentPowerConsumption += subCorridor.getLight().getPowerConsumption();
            }
        }
        return currentPowerConsumption;
    }
}
