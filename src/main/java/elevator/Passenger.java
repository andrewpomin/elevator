package elevator;

public class Passenger {
    private final String name;
    private Direction direction;
    private short currentFloor;
    private short targetFloor;

    Passenger(short count, short floor) {
        //Create name
        name = "passenger" + count;

        //Set current floor
        setCurrentFloor(floor);

        //Generate random direction
        generateDirection();

        //Generate random target floor according to max floors in building
        generateTargetFloor();

        System.out.println("Create " + name + " want to " + targetFloor + " floor");
    }

    //Getters and Setters
    public String getName() {return name;}
    public Direction getDirection() {return direction;}
    public void setDirection(Direction direction) {this.direction = direction;}
    public short getCurrentFloor() {return currentFloor;}
    public void setCurrentFloor(short currentFloor) {this.currentFloor = currentFloor;}
    public short getTargetFloor() {return targetFloor;}
    public void setTargetFloor(short targetFloor) {this.targetFloor = targetFloor;}

    void generateDirection() {
        if (Math.random() < 0.5 && getCurrentFloor() != 1) {
            setDirection(Direction.DOWN); //if random < 0.5 and passenger not on the first floor
        } else if (getCurrentFloor() != Building.getFloorCount()) {
            setDirection(Direction.UP); //If random > 0.5 or/and passenger on first floor
        } else {
            setDirection(Direction.DOWN); //if passenger on the last floor
        }
    }

    void generateTargetFloor() {
        short random;
        do {
            random = (short) (Math.random() * (Building.getFloorCount() - 1) + 1);
        } while (random == getCurrentFloor());
        setTargetFloor(random);
    }
}
