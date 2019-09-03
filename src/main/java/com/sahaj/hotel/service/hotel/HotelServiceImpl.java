package com.sahaj.hotel.service.hotel;

import com.sahaj.hotel.exception.InvalidMotionRequest;
import com.sahaj.hotel.model.enums.EquipmentState;
import com.sahaj.hotel.model.hotel.Floor;
import com.sahaj.hotel.model.hotel.Hotel;
import com.sahaj.hotel.model.hotel.corridor.SubCorridor;
import com.sahaj.hotel.model.request.MotionEvent;
import com.sahaj.hotel.service.floor.FloorService;
import com.sahaj.hotel.service.floor.FloorServiceImpl;
import com.sahaj.hotel.util.Validator;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.sahaj.hotel.model.ModelConstants.SCHEDULE_TIME_IN_MILLIS;
import static com.sahaj.hotel.model.ModelConstants.TIME_IN_MILLIS_TO_SWITCH_OFF;

public class HotelServiceImpl implements HotelService {

    private FloorService floorService = new FloorServiceImpl();

    @Override
    public void printHotelState(Hotel hotel) {
        System.out.println("**********HOTEL STATE**********");
        hotel.getFloors().forEach(floor -> {
            System.out.println("Floor " + floor.getFloorNo());
            floor.getMainCorridors().forEach(mainCorridor -> System.out.println("Main Corridor " + mainCorridor.getCorridorId() + " Light " + mainCorridor.getCorridorId() + ": " + mainCorridor.getLight().getState().name() + " AC " + mainCorridor.getAc().getState().name()));
            floor.getSubCorridors().forEach(subCorridor -> System.out.println("Sub Corridor " + subCorridor.getCorridorId() + " Light " + subCorridor.getCorridorId() + ": " + subCorridor.getLight().getState().name() + " AC " + subCorridor.getAc().getState().name()));
        });
        System.out.println("********************************");
    }

    @Override
    public Hotel handleMotionEvent(MotionEvent motionEvent) throws InvalidMotionRequest {
        Validator.validateMotionRequest(motionEvent);
        Hotel hotel = motionEvent.getHotel();
        Floor floor = hotel.getFloors().get(motionEvent.getFloorNo() - 1);
        List<SubCorridor> subCorridors = floor.getSubCorridors();
        SubCorridor subCorridor = subCorridors.get(motionEvent.getSubCorridorNo() - 1);
        if (subCorridor.getLight().getState() == EquipmentState.OFF) {
            subCorridor.getLight().setState(EquipmentState.ON);
            subCorridor.setTimestamp(new Date().getTime());
            checkPowerConsumption(floor, subCorridors, subCorridor);
            scheduleTaskToSwitchOffLights(subCorridors, subCorridor);
        } else {
            subCorridor.setTimestamp(new Date().getTime());
            checkPowerConsumption(floor, subCorridors, subCorridor);
            scheduleTaskToSwitchOffLights(subCorridors, subCorridor);
        }
        return hotel;
    }

    private void scheduleTaskToSwitchOffLights(List<SubCorridor> subCorridors, SubCorridor subCorridor) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (new Date().getTime() - subCorridor.getTimestamp() >= TIME_IN_MILLIS_TO_SWITCH_OFF) {
                    subCorridor.getLight().setState(EquipmentState.OFF);
                    for (SubCorridor sub : subCorridors) {
                        if (sub.getAc().getState() == EquipmentState.OFF && !sub.equals(subCorridor)) {
                            sub.getAc().setState(EquipmentState.ON);
                        }
                    }
                }
            }
        };
        Timer timer = new Timer("Timer");
        timer.schedule(task, SCHEDULE_TIME_IN_MILLIS);
    }

    private void checkPowerConsumption(Floor floor, List<SubCorridor> subCorridors, SubCorridor subCorridor) {
        int powerConsumption = floorService.calculateCurrentPowerConsumption(floor.getMainCorridors(), subCorridors);
        int maxPowerConsumption = floor.getMaxPowerConsumption();
        if (maxPowerConsumption < powerConsumption) {
            for (SubCorridor sub : subCorridors) {
                if (sub.getAc().getState() == EquipmentState.ON && !sub.equals(subCorridor)) {
                    sub.getAc().setState(EquipmentState.OFF);
                }
            }
        }
    }
}
