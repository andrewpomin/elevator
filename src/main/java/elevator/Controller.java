package elevator;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Controller {
    private Building building;
    private Elevator elevator;

    //Use this list for temporary holding passenger who just arrived at the floor, he can't come in elevator again
    private List<Passenger> temporary;

    Controller() {
        setBuilding(new Building());
        setElevator(building.getElevator());
        setTemporary(new ArrayList<>());
    }

    //Getters and Setters
    Building getBuilding() {return building;}
    void setBuilding(Building building) {this.building = building;}
    Elevator getElevator() {return elevator;}
    void setElevator(Elevator elevator) {this.elevator = elevator;}
    List<Passenger> getTemporary() {return temporary;}
    void setTemporary(List<Passenger> list) {this.temporary = list;}
    void addToTemporary(Passenger passenger) {temporary.add(passenger);}
    void clearTemporary() {temporary.clear();}

    //Elevator work
    void makeStep() {
        try {
            leaveElevator(); //Passengers leave elevator
            getNewDirection(); //Check, if in elevator no passengers - generate new direction
            comeInElevator(); //People come in elevator
            addPassengersToTheFloor(); //Add passengers who just arrived
            makeMove(); //Elevator goes to the next floor
            printStep(); //Print this step
            sleep(3000); //Wait
        } catch (InterruptedException e) {
            LogWriter.writeLog("Thread was interrupted\n");
        }
    }

    //Elevator move on one floor in its direction
    void makeMove() {
        getElevator().setCurrentFloor();
        updateLocation();
    }

    //Update location for passengers in elevator
    void updateLocation() {
        for(Passenger p: getElevator().getPassengersList()) {
            p.setCurrentFloor(getElevator().getCurrentFloor());
        }
    }

    //Add passengers who just arrived to the floor
    void addPassengersToTheFloor() {
        Floor floor = getBuilding().getFloor(getElevator().getCurrentFloor());
        for (Passenger p : getTemporary()) {
            floor.addPassenger(p);
        }
        clearTemporary();
    }

    //Passengers leave elevator
    void leaveElevator() {
        //If elevator has passengers
        if (getElevator().getPassengersList() != null) {
            List<Passenger> list = getElevator().getPassengersList();

            //Iterate all passengers in elevator
            for (int i = 0; i < list.size(); i++) {
                Passenger passenger = list.get(i);

                //If passenger want at the current floor
                if (passenger.getTargetFloor() == getElevator().getCurrentFloor()) {
                    getElevator().removePassenger(passenger);
                    addToTemporary(passenger);
                    passenger.generateTargetFloor(); //Generate new need
                    --i;
                }
            }
        }
    }

    //Passengers leave floor
    void comeInElevator() {
        Floor floor = getBuilding().getFloor(getElevator().getCurrentFloor());
        List<Passenger> list = floor.getPassengersList();

        //If there are passengers on this floor
        if (list != null) {

            //Iterate all passengers at the floor
            for (int i = 0; i < list.size(); i++) {

                //If elevator has free space
                if (getElevator().hasFreeSpace()) {
                    Passenger passenger = list.get(i);

                    //If passenger have same direction as elevator
                    if (passenger.getDirection().equals(getElevator().getDirection())) {
                        floor.removePassenger(passenger);
                        getElevator().addPassenger(passenger);
                        --i;
                    }
                } else {
                    break;
                }
            }

            //Recalculate target floor for elevator
            getElevator().calculateTargetFloor();
        }
    }

    //When all passengers leave elevator - generate new direction
    void getNewDirection() {
        //If elevator reach target floor
        if (getElevator().getCurrentFloor() == getElevator().getTargetFloor()) {
            List<Passenger> list = getBuilding().getFloor(getElevator().getCurrentFloor()).getPassengersList();

            //If there are no one at the floor
            if (list.size() == 0) {

                //If this is the last floors (first or last)
                if (getElevator().getCurrentFloor() == 1 || getElevator().getCurrentFloor() == Building.getFloorCount()) {

                    //Choose direction from last floors
                    if (getElevator().getCurrentFloor() == 1) {
                        getElevator().setDirection(Direction.UP);
                    } else {
                        getElevator().setDirection(Direction.DOWN);
                    }

                    //Moves on one floor next
                    getNewTargetMovingFromLastFloors();

                //This is not the last floors
                } else {

                    //Is elevator moves from empty last floors
                    if (getElevator().isMovesFromLastFloors()) {
                        getNewTargetMovingFromLastFloors();
                    } else {
                        getNewTarget();
                    }
                }

            //If there are passengers on this floor - generate direction according to majority
            } else {
                getElevator().generateDirection(list);
                getElevator().setMovesFromLastFloors(false);
            }
        }
    }

    //If elevator on empty floor with no people - it goes to first or last floor, because they can wait elevator forever
    void getNewTarget() {
        //If direction was up and this is not the last floor
        if (getElevator().getDirection().equals(Direction.UP)) {
            getElevator().setTargetFloor(Building.getFloorCount());

            //If direction was down or thi is the last floor
        } else {
            getElevator().setTargetFloor((short) 1);
        }
    }

    /* If elevator on the first or lst floor and there are no people - move on one next, if on next floor no people -
    moves on one again */
    void getNewTargetMovingFromLastFloors() {
        //If this is the max floor - set previous floor
        if (getElevator().getDirection().equals(Direction.DOWN)) {
            getElevator().setTargetFloor((short) (getElevator().getCurrentFloor() - 1));
            getElevator().setMovesFromLastFloors(true);

        //If direction this is the last floor
        } else {
            getElevator().setTargetFloor((short) (getElevator().getCurrentFloor() + 1));
            getElevator().setMovesFromLastFloors(true);
        }
    }

    //Print step results
    void printStep() {
        getBuilding().printBuilding();
    }
}
