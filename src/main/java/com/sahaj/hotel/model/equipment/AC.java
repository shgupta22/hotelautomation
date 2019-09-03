package com.sahaj.hotel.model.equipment;

import com.sahaj.hotel.model.ModelConstants;
import com.sahaj.hotel.model.enums.EquipmentState;
import lombok.Data;

@Data
public class AC extends Equipment {
    public AC(EquipmentState state) {
        super(state, ModelConstants.AC_POWER_CONSUMPTION);
    }
}
