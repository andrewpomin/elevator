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

        LogWriter.writeLog("Create floor " + floor + " with " + passengerCount + " passengers\n");

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
    void addPassenger(Passenger passenger) {getPassengersList().add(passenger); ++passengerCount;}

    //When someone move from this floor
    void removePassenger(Passenger passenger) {getPassengersList().remove(passenger); --passengerCount;}

    //Print floor
    void printFloor(Elevator elevator) {
        //Print floor number
        if (floor < 10) {
            System.out.print("0" + floor + " | ");
        } else {
            System.out.print(floor + " | ");
        }

        //Print elevator
        if (elevator.getCurrentFloor() == getFloor()) {
            elevator.printElevator();
        } else {
            System.out.print("                                                         "); //If elevator on other floor
        }

        //Print a wall
        System.out.print(" |  ");

        //Print passengers
        for (Passenger p2 : getPassengersList()) {
            p2.printPassenger();
        }

        System.out.print("\n"); //Next floor
    }
}
