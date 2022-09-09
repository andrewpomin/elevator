package building;

import enums.Direction;
import writers.LogWriter;

public class Passenger {
    private final String name;
    private Direction direction;
    private short currentFloor;
    private short targetFloor;

    Passenger(short count, short floor) {
        //Create name
        name = "p" + count;

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
    public short getCurrentFloor() {return currentFloor;}
    public void setCurrentFloor(short currentFloor) {this.currentFloor = currentFloor;}
    public short getTargetFloor() {return targetFloor;}
    public void setTargetFloor(short targetFloor) {this.targetFloor = targetFloor;}

    //Generate random next target floor
    void generateTargetFloor() {
        short random;
        do {
            random = (short) (Math.random() * (Building.getFloorCount() - 1) + 1);
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
            floor = "0" + getTargetFloor();
        } else {
            floor = String.valueOf(getTargetFloor());
        }

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
