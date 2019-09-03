package com.sahaj.hotel.model.hotel;

import com.sahaj.hotel.model.hotel.corridor.MainCorridor;
import com.sahaj.hotel.model.hotel.corridor.SubCorridor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Floor {
    private int floorNo;
    private List<MainCorridor> mainCorridors;
    private List<SubCorridor> subCorridors;
    private int maxPowerConsumption;

    public Floor(int floorNo, int noOfMainCorridor, int noOfSubCorridor) {
        this.floorNo = floorNo;
        this.mainCorridors = createMainCorridors(noOfMainCorridor);
        this.subCorridors = createSubCorridors(noOfSubCorridor);
        this.maxPowerConsumption = calculateMaxPowerConsumption(noOfMainCorridor, noOfSubCorridor);
    }

    private List<MainCorridor> createMainCorridors(int noOfMainCorridor) {
        List<MainCorridor> corridors = new ArrayList<>();
        for (int i = 0; i < noOfMainCorridor; i++) {
            corridors.add(new MainCorridor(i+1));
        }
        return corridors;
    }

    private List<SubCorridor> createSubCorridors(int noOfSubCorridor) {
        List<SubCorridor> subCorridors = new ArrayList<>();
        for (int i = 0; i < noOfSubCorridor; i++) {
            subCorridors.add(new SubCorridor(i+1));
        }
        return subCorridors;
    }

    private int calculateMaxPowerConsumption(int noOfMainCorridor, int noOfSubCorridor) {
        return noOfMainCorridor * 15 + noOfSubCorridor * 10;
    }
}
