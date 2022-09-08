package elevator;

import static java.lang.Thread.sleep;

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
        try {
            leaveElevator();
            comeInElevator();
            makeMove();
            printStep();
            sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted.");
        }
    }

    void makeMove() {
        elevator.setCurrentFloor();
    }

    void leaveElevator() {
        if (elevator.getPassengersList() != null) {
            Floor floor = building.getFloor(elevator.getCurrentFloor());
            for (Passenger p : elevator.getPassengersList()) {
                p.setCurrentFloor(elevator.getCurrentFloor());
                if (p.getTargetFloor() == elevator.getCurrentFloor()) {
                    elevator.removePassenger(p);
                    floor.addPassenger(p);
                }
            }
        }
    }

    void comeInElevator() {
        Floor floor = building.getFloor(elevator.getCurrentFloor());
        if (floor.getPassengersList() != null) {
            if (elevator.hasFreeSpace()) {
                for (Passenger p : floor.getPassengersList()) {
                    if (p.getDirection().equals(elevator.getDirection())) {
                        floor.removePassenger(p);
                        elevator.addPassenger(p);
                    }
                }
            }
        }
    }

    void printStep() {
        building.printBuilding();
    }
}
