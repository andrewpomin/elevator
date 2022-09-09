package writers;

import java.io.FileWriter;
import java.io.IOException;

public class Printer {
    static {
        try {
            FileWriter fw = new FileWriter("elevator_work.txt");
            fw.close();
        } catch (IOException e) {
            System.out.println("Catch an exception");
        }
    }

    public static void print(String string) {
        try {
            FileWriter fw = new FileWriter("elevator_work.txt", true);
            fw.write(string);
            fw.close();
        } catch (IOException e) {
            System.out.println("Catch an exception");
        }
    }
}
