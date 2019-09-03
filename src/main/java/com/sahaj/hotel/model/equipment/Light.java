package com.sahaj.hotel.model.equipment;

import com.sahaj.hotel.model.ModelConstants;
import com.sahaj.hotel.model.enums.EquipmentState;
import lombok.Data;

@Data
public class Light extends Equipment {
    public Light(EquipmentState state) {
        super(state, ModelConstants.LIGHT_POWER_CONSUMPTION);
    }
}


