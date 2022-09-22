package building;

import writers.LogWriter;
import writers.Printer;

import java.io.Console;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        Console console = System.console();
        int count;
        if (console != null) {
            count = Integer.parseInt(console.readLine("Type count of steps you want: "));
        } else {
            count = 10; //Default - 10 steps
        }
        Controller controller = new Controller();
        System.out.println("     ------------------------- Start -------------------------\n");
        Printer.print("     ------------------------- Start -------------------------\n");
        LogWriter.writeLog("Start:\n");
        controller.printStep();
        try {
            sleep(3000);
            for (int i = 1; i <= count; i++) {
                System.out.println("\n     ------------------------ Step " + i + " ------------------------\n");
                Printer.print("\n     ------------------------ Step " + i + " ------------------------\n");
                LogWriter.writeLog("Step " + i + ":\n");
                controller.makeStep();
            }
            String end = "\nLift is tired!";
            LogWriter.writeLog(end);
            Printer.print(end);
            System.out.println(end);
        } catch (InterruptedException e) {
            LogWriter.writeLog("Thread was interrupted.\n");
        }
    }
}
