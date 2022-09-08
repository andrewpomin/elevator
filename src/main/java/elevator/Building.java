package elevator;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private static short floorCount;
    private static List<Floor> floorList;
    private static short totalPassengersInBuilding = 0;
    private final Elevator elevator;

    Building() {
        //Generate random floor count
        floorCount = (short) ((Math.random() * 15) + 5);

        //Create floors
        floorList = generateFloors();

        //Create elevator
        elevator = new Elevator();
    }

    //Getters and Setters
    static short getFloorCount() {return floorCount;}
    static List<Floor> getFloorList() {return floorList;}
    Floor getFloor(short floor) {
        for (Floor f : getFloorList()) {
            if (f.getFloor() == floor) {
                return f;
            }
        }

        return null;
    }
    static short getTotalPassengersInBuilding() {return totalPassengersInBuilding;}
    void addTotalPassengersInBuilding(short count) {totalPassengersInBuilding += count;}
    Elevator getElevator() {return elevator;}

    //Create floors in the building
    List<Floor> generateFloors() {
        List<Floor> temporary = new ArrayList<>(getFloorCount());

        for (short i = 0; i < floorCount; i++) {
            Floor floor = new Floor(i);
            temporary.add(floor); //Add floor in the list
            addTotalPassengersInBuilding(floor.getPassengerCount()); //Add generated passengers in total count
        }

        return temporary;
    }

    List<Passenger> getPassengersList() {
        List<Passenger> list = new ArrayList<>(getTotalPassengersInBuilding());

        for (Floor f : getFloorList()) {
            list.addAll(f.getPassengersList());
        }

        return list;
    }
}
