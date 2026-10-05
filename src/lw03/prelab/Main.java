import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Problem 1: Playlist (List)
        Scanner sc = new Scanner(new File("playlist.txt"));
        List<String> playlist = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] p = line.split(" ", 2);
            if (p[0].equals("ADD")) playlist.add(p[1]);
            else if (p[0].equals("INSERT")) {
                String[] q = p[1].split(" ", 2);
                playlist.add(Integer.parseInt(q[0]), q[1]);
            } else if (p[0].equals("REMOVE")) playlist.remove(p[1]);
        }
        sc.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) System.out.println((i + 1) + ": " + playlist.get(i));

        // Problem 2: Workshop participants (Set)
        sc = new Scanner(new File("participants.txt"));
        Set<String> participants = new LinkedHashSet<>();
        int dup = 0;
        while (sc.hasNextLine()) {
            String name = sc.nextLine().trim();
            if (name.isEmpty()) continue;
            if (!participants.add(name)) dup++;
        }
        sc.close();

        System.out.println("\n===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int n = 1;
        for (String name : participants) System.out.println((n++) + ". " + name);
        System.out.println("Duplicate registrations: " + dup);

        // Problem 3: Product inventory (Map)
        sc = new Scanner(new File("inventory.txt"));
        Map<String, Integer> stock = new LinkedHashMap<>();
        int failed = 0;
        while (sc.hasNext()) {
            String type = sc.next(), product = sc.next();
            int qty = sc.nextInt();
            if (type.equals("ADD")) stock.put(product, stock.getOrDefault(product, 0) + qty);
            else if (type.equals("SELL")) {
                if (stock.containsKey(product) && stock.get(product) >= qty) stock.put(product, stock.get(product) - qty);
                else failed++;
            }
        }
        sc.close();

        System.out.println("\n===== Problem 3 =====");
        for (Map.Entry<String, Integer> e : stock.entrySet()) System.out.println(e.getKey() + ": " + e.getValue());
        System.out.println("Failed sales: " + failed);
    }
}
