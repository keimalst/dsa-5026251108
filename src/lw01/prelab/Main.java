import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<PrintJob> jobs = new ArrayList<>();

        try (Scanner sc = new Scanner(new File("jobs.txt"))) {
            while (sc.hasNext()) {
                String t = sc.next(), id = sc.next();
                int p = sc.nextInt();
                jobs.add(t.equals("MONO") ? new MonoPrint(id, p) : new ColourPrint(id, p));
            }
        }

        for (PrintJob j : jobs) System.out.println(j.summary());
    }
}
