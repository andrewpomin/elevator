package elevator;

import java.util.List;

public class Elevator {
    private Direction direction;
    private short currentFloor = 1;
    private short maxFloor;
    private final byte maxPassenger = 5;
    List<Passenger> passengersList;

    //Getters and Setters
    public Direction getDirection() {return direction;}
    public void setDirection(Direction direction) {this.direction = direction;}
    public short getCurrentFloor() {return currentFloor;}
    public void setCurrentFloor() {if (getDirection().equals(Direction.UP)) {++currentFloor;} else {--currentFloor;}}
    public short getMaxFloor() {return maxFloor;}
    public void setMaxFloor(short maxFloor) {this.maxFloor = maxFloor;}
    public byte getMaxPassenger() {return maxPassenger;}
    public List<Passenger> getPassengersList() {return passengersList;}
    public boolean hasFreeSpace() {return getPassengersList().size() != getMaxPassenger();}

    //Generate direction according to the passengers needs
    void generateDirection(Direction currentDirection) {
        byte counter = 0;
        for (Passenger p : getPassengersList()) {
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
    void generateMaxFloor() {
        if (getDirection().equals(Direction.UP)) {
            for (Passenger p : getPassengersList()) {
                if (p.getTargetFloor() > getMaxFloor()) {
                    setMaxFloor(p.getTargetFloor());
                }
            }
        } else {
            for (Passenger p : getPassengersList()) {
                if (p.getTargetFloor() < getMaxFloor()) {
                    setMaxFloor(p.getTargetFloor());
                }
            }
        }
    }

    //When someone came in elevator on this floor
    void addPassenger(Passenger passenger) {getPassengersList().add(passenger);}
    //When someone move from elevator
    void removePassenger(Passenger passenger) {getPassengersList().remove(passenger);}
}
