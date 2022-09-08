package elevator;

public class Controller {
    Building building;
    Elevator elevator;

    Controller() {
        building = new Building();
        elevator = building.getElevator();
    }

    Building getBuilding() {return building;}
    Elevator getElevator() {return elevator;}

    void makeStep() {
        makeMove();
        leaveElevator();
        comeInElevator();
    }

    void makeMove() {
        elevator.setCurrentFloor();
    }

    void leaveElevator() {
        Floor floor = building.getFloor(elevator.getCurrentFloor());
        for (Passenger p : elevator.getPassengersList()) {
            p.setCurrentFloor(elevator.getCurrentFloor());
            if (p.getTargetFloor() == elevator.getCurrentFloor()) {
                elevator.removePassenger(p);
                floor.addPassenger(p);
            }
        }
    }

    void comeInElevator() {
        Floor floor = building.getFloor(elevator.getCurrentFloor());
        if (elevator.hasFreeSpace()) {
            for (Passenger p : floor.getPassengersList()) {
                if (p.getDirection().equals(elevator.getDirection())) {
                    floor.removePassenger(p);
                    elevator.addPassenger(p);
                }
            }
        }
    }

    void printStep() {
        
    }
}
