package elevator;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final short floor;
    private short passengerCount;
    private List<Passenger> passengersList;

    Floor(short floor) {
        //Give number of a floor
        this.floor = floor;

        //Generate random count of passengers
        setPassengerCount((short) (Math.random() * 10));

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
            passengersList.add(passenger); //Add passenger to the list
        }

        return temporary;
    }

    //When someone came from elevator on this floor
    void addPassenger(Passenger passenger) {passengersList.add(passenger);}

    //When someone move from this floor
    void removePassenger(Passenger passenger) {passengersList.remove(passenger);}
}
