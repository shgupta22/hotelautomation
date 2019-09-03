package com.sahaj.hotel.model.hotel.corridor;

import com.sahaj.hotel.model.enums.EquipmentState;
import com.sahaj.hotel.model.equipment.AC;
import com.sahaj.hotel.model.equipment.Light;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MainCorridor extends Corridor {
    private Light light;
    private AC ac;

    public MainCorridor(int corridorId) {
        super(corridorId);
        this.light = new Light(EquipmentState.ON);
        this.ac = new AC(EquipmentState.ON);
    }
}
