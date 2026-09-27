import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        // 1. Read and store transactions
        try (Scanner file = new Scanner(new File("src/lw02/prelab/transactions.txt"))) {
            while (file.hasNextLine()) {
                String line = file.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                transactions.add(parts);

                // 2. Register customer on first appearance, initial balance 0
                if (findCustomer(customers, parts[0]) == null) {
                    customers.add(new String[]{parts[0], "0"});
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt tidak ditemukan.");
            return;
        }

        // 3. Move transactions into a Queue and process in FIFO order
        Queue<String[]> queue = new LinkedList<>(transactions);
        Stack<String[]> failed = new Stack<>();

        while (!queue.isEmpty()) {
            String[] tx = queue.poll();
            String name = tx[0], type = tx[1];
            int amount = Integer.parseInt(tx[2]);

            String[] customer = findCustomer(customers, name);
            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    failed.push(tx);
                } else {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        // 5. Display results
        System.out.println("=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failed.isEmpty()) {
            String[] tx = failed.pop();
            System.out.println(tx[0] + " " + tx[1] + " " + tx[2]);
        }
    }

    private static String[] findCustomer(LinkedList<String[]> customers, String name) {
        for (String[] c : customers) {
            if (c[0].equals(name)) return c;
        }
        return null;
    }
}
