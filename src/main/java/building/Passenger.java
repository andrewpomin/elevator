package building;

import enums.Direction;
import writers.LogWriter;

public class Passenger {
    private final Building building;
    private final String name;
    private Direction direction;
    private int currentFloor;
    private int targetFloor;

    Passenger(int count, int floor, Building building) {
        //Create name
        name = "p" + count;

        this.building = building;

        LogWriter.writeLog("Create passenger " + name + "\n");

        //Set current floor
        setCurrentFloor(floor);

        //Generate random target floor according to max floors in building
        generateTargetFloor();
    }

    //Getters and Setters
    public String getName() {return name;}
    public Direction getDirection() {return direction;}
    public void setDirection(Direction direction) {this.direction = direction;}
    public int getCurrentFloor() {return currentFloor;}
    public void setCurrentFloor(int currentFloor) {this.currentFloor = currentFloor;}
    public int getTargetFloor() {return targetFloor;}
    public void setTargetFloor(int targetFloor) {this.targetFloor = targetFloor;}

    //Generate random next target floor
    void generateTargetFloor() {
        int random;
        do {
            random = (int) (Math.random() * (building.getFloorCount() - 1) + 1); //From 0 to max floor
        } while (random == getCurrentFloor());
        setTargetFloor(random);
        LogWriter.writeLog("Passenger " + name + " has new target floor " + getTargetFloor());
        generateDirection();
    }

    //Generate direction according to target floor
    void generateDirection() {
        if (getTargetFloor() - getCurrentFloor() > 0) {
            setDirection(Direction.UP);
            LogWriter.writeLog(" and direction UP\n");
        } else {
            setDirection(Direction.DOWN);
            LogWriter.writeLog(" and direction DOWN\n");
        }
    }

    //Print passenger
    StringBuilder printPassenger() {
        String floor;
        StringBuilder sb = new StringBuilder();
        if (getTargetFloor() < 10) {
            floor = "0" + getTargetFloor(); //If floor < 10 - write 0 at the beginning
        } else {
            floor = String.valueOf(getTargetFloor());
        }

        //For text align
        if(Short.parseShort(name.substring(1)) < 10) {
            sb.append("  ").append(name).append("->").append(floor).append("  ");
        } else if (Short.parseShort(name.substring(1)) < 100) {
            sb.append(" ").append(name).append("->").append(floor).append("  ");
        } else {
            sb.append(name).append("->").append(floor);
        }

        return sb;
    }
}
