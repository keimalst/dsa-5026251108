package lw02.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("orders.txt"));

        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> food = new LinkedList<>();
        LinkedList<String[]> drink = new LinkedList<>();
        LinkedList<String[]> success = new LinkedList<>();
        Queue<String[]> processes = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        food.add(new String[]{"2", "1", "2"}); 

        drink.add(new String[]{"4", "2"}); 

        while (sc.hasNext()) {
            String name = sc.next();
            if (name.equals("-")) {
                break;
            }
            String sD = sc.next();
            String d = sc.next();
            String table = sc.next();

            String[] theOrder = new String[]{name, sD, d, table};
            orders.add(theOrder);
        }

        while (!orders.isEmpty()) {
            processes.add(orders.removeFirst());
        }

        String[] foodStock = food.getFirst();
        String[] drinkStock = drink.getFirst();

        while (!processes.isEmpty()) {
            String[] currentOrder = processes.poll();
            String foodReq = currentOrder[1];
            String drinkReq = currentOrder[2];

            int stokBakso = Integer.parseInt(foodStock[0]);
            int stokSate = Integer.parseInt(foodStock[1]);
            int stokSoto = Integer.parseInt(foodStock[2]);

            int stokEsTeh = Integer.parseInt(drinkStock[0]);
            int stokEsJeruk = Integer.parseInt(drinkStock[1]);

            boolean foodAvailable = false;
            boolean drinkAvailable = false;

            if (foodReq.equals("Bakso") && stokBakso > 0) foodAvailable = true;
            else if (foodReq.equals("Sate") && stokSate > 0) foodAvailable = true;
            else if (foodReq.equals("Soto") && stokSoto > 0) foodAvailable = true;

            if (drinkReq.equals("EsTeh") && stokEsTeh > 0) drinkAvailable = true;
            else if (drinkReq.equals("EsJeruk") && stokEsJeruk > 0) drinkAvailable = true;

            if (foodAvailable && drinkAvailable) {
                if (foodReq.equals("Bakso")) foodStock[0] = String.valueOf(stokBakso - 1);
                else if (foodReq.equals("Sate")) foodStock[1] = String.valueOf(stokSate - 1);
                else if (foodReq.equals("Soto")) foodStock[2] = String.valueOf(stokSoto - 1);

                if (drinkReq.equals("EsTeh")) drinkStock[0] = String.valueOf(stokEsTeh - 1);
                else if (drinkReq.equals("EsJeruk")) drinkStock[1] = String.valueOf(stokEsJeruk - 1);

                success.add(currentOrder);
            } else {
                failed.push(currentOrder);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] proces : success) {
            for (String word : proces) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("=== Remaining Food Stock ===");
        for (String[] item : food) {
            System.out.println("Bakso: " + item[0] + " | Sate: " + item[1] + " | Soto: " + item[2]);
        }
        System.out.println();

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] item : drink) {
            System.out.println("EsTeh: " + item[0] + " | EsJeruk: " + item[1]);
        }
        System.out.println();

        System.out.println("=== Failed Orders ===");
        for (String[] fail : failed) {
            for (String word : fail) {
                System.out.print(word + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}