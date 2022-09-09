package elevator;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println("\n     ------------------------- Start -------------------------\n");
        LogWriter.writeLog("Start:\n");
        controller.printStep();
        try {
            sleep(3000);
            for (int i = 1; i <= 100; i++) {
                System.out.println("\n     ------------------------ Step " + i + " ------------------------\n");
                LogWriter.writeLog("Step " + i + ":\n");
                controller.makeStep();
            }
            LogWriter.writeLog("Lift is tired!");
        } catch (InterruptedException e) {
            LogWriter.writeLog("Thread was interrupted.\n");
        }
    }
}
