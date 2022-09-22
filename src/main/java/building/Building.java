package building;

import writers.LogWriter;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private final int floorCount;
    private final List<Floor> floorList;
    private short totalPassengersInBuilding = 0;
    private final Elevator elevator;

    Building() {
        //Generate random floor count (form 5 to 20)
        floorCount = (short) ((Math.random() * 15) + 5);

        LogWriter.writeLog("Create building with " + floorCount + " floors\n");

        //Create floors
        floorList = generateFloors();

        //Create elevator
        elevator = new Elevator();
    }

    //Getters and Setters
    public int getFloorCount() {return floorCount;}
    public List<Floor> getFloorList() {return floorList;}
    public Floor getFloor(int floor) {
        for (Floor f : getFloorList()) {
            if (f.getFloor() == floor) {
                return f;
            }
        }

        return null;
    }
    public short getTotalPassengersInBuilding() {return totalPassengersInBuilding;}
    void addTotalPassengersInBuilding(int count) {totalPassengersInBuilding += count;}
    Elevator getElevator() {return elevator;}

    //Create floors in the building
    List<Floor> generateFloors() {
        List<Floor> temporary = new ArrayList<>(getFloorCount());

        for (int i = 1; i <= floorCount; i++) {
            Floor floor = new Floor(i, this);
            temporary.add(floor); //Add floor in the list
            addTotalPassengersInBuilding(floor.getPassengerCount()); //Add generated passengers in total count
        }

        return temporary;
    }

    //Print building
    void printBuilding() {
        for (int i = getFloorCount(); i > 0; i--) {
            getFloor(i).printFloor(getElevator());
        }
    }
}
