package com.sahaj.hotel.model.hotel.corridor;

import com.sahaj.hotel.model.enums.EquipmentState;
import com.sahaj.hotel.model.equipment.AC;
import com.sahaj.hotel.model.equipment.Light;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class SubCorridor extends Corridor {
    private Light light;
    private AC ac;
    @Setter
    private long timestamp;

    public SubCorridor(int corridorId) {
        super(corridorId);
        this.light = new Light(EquipmentState.OFF);
        this.ac = new AC(EquipmentState.ON);
        timestamp = new Date().getTime();
    }
}
