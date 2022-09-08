package elevator;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final short floor;
    private short passengerCount;
    private List<Passenger> passengersList = new ArrayList<>();

    Floor(short floor) {
        //Give number of a floor
        this.floor = floor;

        //Generate random count of passengers
        setPassengerCount((short) (Math.random() * 10));

        System.out.println("Create floor " + floor + " with " + passengerCount + " passengers");

        //Generate passengers at the start
        setPassengersList(generatePassengers());
    }

    //Getters and Setters
    public short getFloor() {return floor;}
    public short getPassengerCount() {return passengerCount;}
    public void setPassengerCount(short passengerCount) {this.passengerCount = passengerCount;}
    public List<Passenger> getPassengersList() {return passengersList;}
    public void setPassengersList(List<Passenger> passengersList) {this.passengersList = passengersList;}

    //Create passenger at the start
    List<Passenger> generatePassengers() {
        List<Passenger> temporary = new ArrayList<>(getPassengerCount());
        short countFrom = (short) (Building.getTotalPassengersInBuilding() + 1); //Get from which number continue

        for (short i = countFrom; i < countFrom + passengerCount; i++) {
            Passenger passenger = new Passenger(i, floor);
            temporary.add(passenger); //Add passenger to the list
        }

        return temporary;
    }

    //When someone came from elevator on this floor
    void addPassenger(Passenger passenger) {passengersList.add(passenger);}

    //When someone move from this floor
    void removePassenger(Passenger passenger) {passengersList.remove(passenger);}

    void printFloor(Elevator elevator) {
        //Print elevator
        if (elevator.getCurrentFloor() == getFloor()) {
            elevator.printElevator();
        } else {
            System.out.print("                                              ");
        }

        //Print a wall
        System.out.print(" |  ");

        //Print passengers
        for (Passenger p2 : getPassengersList()) {
            System.out.print(p2.getName() + "(" + p2.getTargetFloor() + ") ");
        }
        System.out.print("\n");
    }
}
