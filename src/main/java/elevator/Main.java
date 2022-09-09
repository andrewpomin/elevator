package elevator;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println("\n     ------------------------- Start -------------------------\n");
        controller.printStep();
        for (int i = 1; i <= 100; i++) {
            System.out.println("\n     ------------------------ Step " + i + " ------------------------\n");
            controller.makeStep();
        }
    }
}
