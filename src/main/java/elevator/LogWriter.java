package elevator;

import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
    static {
        try {
            FileWriter fw = new FileWriter("elevator_logs.txt");
            writeLog("Let's create elevator!\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Catch an exception");
        }
    }

    public static void writeLog(String log) {
        try {
            FileWriter fw = new FileWriter("elevator_logs.txt", true);
            fw.write(log);
            fw.close();
        } catch (IOException e) {
            System.out.println("Catch an exception");
        }
    }
}
