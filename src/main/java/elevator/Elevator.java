package elevator;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private Direction direction;
    private short currentFloor = 1;
    private short targetFloor;
    private final byte maxPassenger = 5;
    private boolean movesFromLastFloors;
    List<Passenger> passengersList = new ArrayList<>();

    Elevator() {
        setDirection(Direction.UP);

        System.out.println("Create elevator");
    }

    //Getters and Setters
    public Direction getDirection() {return direction;}
    public void setDirection(Direction direction) {this.direction = direction;}
    public short getCurrentFloor() {return currentFloor;}
    public void setCurrentFloor() {if (getDirection().equals(Direction.UP)) {++currentFloor;} else {--currentFloor;}}
    public short getTargetFloor() {return targetFloor;}
    public void setTargetFloor(short targetFloor) {this.targetFloor = targetFloor;}
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
        } else if (counter < 0) {
            setDirection(Direction.DOWN);
        } else {
            setDirection(currentDirection);
        }
    }

    //Generate max floor what needed to passengers
    void calculateTargetFloor() {
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
    }

    //When someone came in elevator on this floor
    void addPassenger(Passenger passenger) {getPassengersList().add(passenger);}
    //When someone move from elevator
    void removePassenger(Passenger passenger) {getPassengersList().remove(passenger);}

    //Print elevator
    void printElevator() {
        //Print direction
        if (getDirection().equals(Direction.UP)) {System.out.print("^");} else {System.out.print("v");}
        System.out.print(" | "); //Left elevator border

        //Print passengers
        for (Passenger p : getPassengersList()) {
            p.printPassenger();
        }

        for (int i = 1; i <= 5 - getPassengersList().size(); i++) {
            System.out.print("          ");
        }

        System.out.print("| "); //Right elevator border

        //Print direction
        if (getDirection().equals(Direction.UP)) {System.out.print("^");} else {System.out.print("v");}
    }
}
