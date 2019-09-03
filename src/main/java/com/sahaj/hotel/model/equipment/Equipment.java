package com.sahaj.hotel.model.equipment;

import com.sahaj.hotel.model.enums.EquipmentState;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Equipment {
    protected EquipmentState state;
    protected int powerConsumption;
}
