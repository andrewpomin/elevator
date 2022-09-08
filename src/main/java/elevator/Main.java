package elevator;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println("-----Start-----");
        controller.printStep();
        for (int i = 1; i <= 1; i++) {
            System.out.println("----- Step " + i + " -----");
            controller.makeStep();
        }
    }
}
