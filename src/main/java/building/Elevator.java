package building;

import enums.Direction;
import writers.LogWriter;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private Direction direction;
    private int currentFloor = 1;
    private int targetFloor;
    private final int maxPassenger = 5; //Max count of passengers in elevator - 5
    private boolean movesFromLastFloors;
    List<Passenger> passengersList = new ArrayList<>();

    Elevator() {
        setDirection(Direction.UP);

        LogWriter.writeLog("Create elevator\n");
    }

    //Getters and Setters
    public Direction getDirection() {return direction;}
    public void setDirection(Direction direction) {this.direction = direction;}
    public int getCurrentFloor() {return currentFloor;}
    public void setCurrentFloor() {if (getDirection().equals(Direction.UP)) {++currentFloor;} else {--currentFloor;}}
    public int getTargetFloor() {return targetFloor;}
    public void setTargetFloor(int targetFloor) {this.targetFloor = targetFloor;}
    public byte getMaxPassenger() {return maxPassenger;}
    public List<Passenger> getPassengersList() {return passengersList;}
    public boolean hasFreeSpace() {return getPassengersList().size() != getMaxPassenger();}
    public boolean isMovesFromLastFloors() {return movesFromLastFloors;}
    public void setMovesFromLastFloors(boolean movesFromLastFloors) {this.movesFromLastFloors = movesFromLastFloors;}

    //Generate direction according to the passengers needs
    void generateDirection(List<Passenger> list) {
        Direction currentDirection = getDirection();
        byte counter = 0;
        for (Passenger p : list) {
            if (p.getDirection().equals(Direction.UP)) {
                ++counter;
            } else {
                --counter;
            }
        }

        if (counter > 0) {
            setDirection(Direction.UP);
            LogWriter.writeLog("Elevator has new direction: UP\n");
        } else if (counter < 0) {
            setDirection(Direction.DOWN);
            LogWriter.writeLog("elevator has new direction: DOWN\n");
        } else {
            setDirection(currentDirection);
            LogWriter.writeLog("Elevator continue move in current direction: " + getDirection() + "\n");
        }
    }

    //Generate max floor what needed to passengers
    void calculateTargetFloor() {
        int previousTarget = getTargetFloor();
        if (getDirection().equals(Direction.UP)) {
            for (Passenger p : getPassengersList()) {
                if (p.getTargetFloor() > getTargetFloor()) {
                    setTargetFloor(p.getTargetFloor());
                }
            }
        } else {
            for (Passenger p : getPassengersList()) {
                if (p.getTargetFloor() < getTargetFloor()) {
                    setTargetFloor(p.getTargetFloor());
                }
            }
        }

        if (previousTarget != getTargetFloor()) {
            LogWriter.writeLog("Elevator has new target floor: " + getTargetFloor() + "\n");
        }
    }

    //When someone came in elevator on this floor
    void addPassenger(Passenger passenger) {getPassengersList().add(passenger);}
    //When someone move from elevator
    void removePassenger(Passenger passenger) {getPassengersList().remove(passenger);}

    //Print elevator
    StringBuilder printElevator() {
        StringBuilder sb = new StringBuilder();
        //Print direction
        if (getDirection().equals(Direction.UP)) {sb.append("^");} else {sb.append("v");}
        sb.append(" | "); //Left elevator border

        //Print passengers
        for (Passenger p : getPassengersList()) {
            sb.append(p.printPassenger());
        }

        sb.append("          ".repeat(Math.max(0, 5 - getPassengersList().size())));

        sb.append("| "); //Right elevator border

        //Print direction
        if (getDirection().equals(Direction.UP)) {sb.append("^");} else {sb.append("v");}

        return sb;
    }
}
