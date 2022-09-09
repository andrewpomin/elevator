package elevator;

public class Passenger {
    private final String name;
    private Direction direction;
    private short currentFloor;
    private short targetFloor;

    Passenger(short count, short floor) {
        //Create name
        name = "p" + count;

        //Set current floor
        setCurrentFloor(floor);

        //Generate random target floor according to max floors in building
        generateTargetFloor();

        //Generate random direction
        generateDirection();

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

    //Generate random next target floor
    void generateTargetFloor() {
        short random;
        do {
            random = (short) (Math.random() * (Building.getFloorCount() - 1) + 1);
        } while (random == getCurrentFloor());
        setTargetFloor(random);
        generateDirection();
    }

    //Generate direction according to target floor
    void generateDirection() {
        if (getTargetFloor() - getCurrentFloor() > 0) {
            setDirection(Direction.UP);
        } else {
            setDirection(Direction.DOWN);
        }
    }

    //Print passenger
    void printPassenger() {
        String floor;
        if (getTargetFloor() < 10) {
            floor = "0" + getTargetFloor();
        } else {
            floor = String.valueOf(getTargetFloor());
        }

        if(Short.parseShort(name.substring(1)) < 10) {
            System.out.print(" " + name + " -> " + floor + " ");
        } else if (Short.parseShort(name.substring(1)) < 100) {
            System.out.print(name + " -> " + floor + " ");
        } else {
            System.out.print(name + "-> " + floor + " ");
        }
    }
}
