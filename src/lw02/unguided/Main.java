package lw02.unguided;

import java.util.*;

public class Main {
        public static void main(String[] args){
                Scanner sc = new Scanner(Main.class.getResourceAsStream("input.txt"));
        
                LinkedList<String[]> orders = new LinkedList<>();
                LinkedList<String[]> food = new LinkedList<>();
                LinkedList<String[]> drink = new LinkedList<>();
                LinkedList<String[]> success = new LinkedList<>();
                Queue<String[]> processes = new LinkedList<>();
                Stack<String[]> failed = new Stack<>();
                
                while (sc.hasNext() && !sc.nextLine().equals("-")) {
                        String name = sc.next();
                        String sD = sc.next();
                        String d = sc.next();
                        String table = sc.next();

                        String[] theOrder = new String[4];
                        theOrder[0] = name;
                        theOrder[1] = sD;
                        theOrder[2] = d;
                        theOrder[3] = table;

                        orders.add(theOrder);

                        String[] foodStock = new String[3];
                        foodStock[0] = "2"; // Bakso 
                        foodStock[1] = "1"; // Sate
                        foodStock[2] = "2"; // Soto

                        food.add(foodStock);

                        String[] drinkStock = new String[3];
                        drinkStock[0] = "4"; // EsTeh
                        drinkStock[1] = "2"; // EsJeruk

                        drink.add(drinkStock);

                }
                
                while (!orders.isEmpty()) {
                processes.add(orders.removeFirst());
                }

                // Process Orders
                for (String[] ss : success){
                for(String[] process : processes){
                        for(String[] f : food){
                        if (ss[0].equals(process[0])){
                                int thisOrder = Integer.parseInt(ss[1]);
                                int stokBakso = Integer.parseInt(f[0]);
                                int stokSate = Integer.parseInt(f[1]);
                                int stokSoto = Integer.parseInt(f[2]);
                                
                                if (Integer.parseInt(ss[1]) < 3){
                                if (stokBakso > 0 && process[1].equals("Bakso")){
                                        stokBakso--;
                                        thisOrder--;

                                        f[0] = String.valueOf(stokBakso);
                                        ss[1] = String.valueOf(thisOrder);
                                }
                                else if (stokSate > 0 && process[1].equals("Sate")){
                                        stokSate--;
                                        thisOrder--;

                                        f[1] = String.valueOf(stokSate);
                                        ss[1] = String.valueOf(thisOrder);
                                }
                                else if (stokSoto > 0 && process[1].equals("Soto")){
                                        stokSoto--;
                                        thisOrder--;

                                        f[2] = String.valueOf(stokSoto);
                                        ss[1] = String.valueOf(thisOrder);
                                }
                                } else {
                                failed.add(0, process);
                                break;
                                }
                        }
                        }
                        for(String[] d : drink){
                        if (ss[0].equals(process[0])){
                                int thisOrder = Integer.parseInt(ss[1]);
                                int stokEsJeruk = Integer.parseInt(d[0]);
                                int stokEsTeh = Integer.parseInt(d[1]);

                                if (Integer.parseInt(ss[1]) < 3){
                                if (stokEsJeruk > 0 && process[1].equals("EsJeruk")){
                                        stokEsJeruk--;
                                        thisOrder--;

                                        d[0] = String.valueOf(stokEsJeruk);
                                        ss[1] = String.valueOf(thisOrder);
                                }
                                else if (stokEsTeh > 0 && process[1].equals("EsTeh")){
                                        stokEsTeh--;
                                        thisOrder--;

                                        d[1] = String.valueOf(stokEsTeh);
                                        ss[1] = String.valueOf(thisOrder);
                                }
                                else {
                                failed.add(0, process);
                                break;
                                }
                        }
                        }
                }
                }

                // ORDER OUTPUT
                System.out.println("=== Successfully Processed Orders ===");
                for (String[] proces : processes){
                        for (String word : proces){
                                System.out.print(word + " ");
                        }
                        System.out.println();
                }
                System.out.println();

                System.out.println("=== Remaining Food Stock ===");
                for (String[] item : food){
                        for (String word : item){
                                System.out.print(word + " ");
                        }
                        System.out.println();
                }

                System.out.println("=== Remaining Drink Stock ===");
                for (String[] item : drink){
                        for (String word : item){
                                System.out.print(word + " ");
                        }
                        System.out.println();
                }

                        System.out.println();

                System.out.println("=== Failed Orders ===");
                for (String[] fail : failed){
                        for (String word : fail){
                                System.out.print(word + " ");
                        }
                System.out.println();
        }

}
        sc.close();
        }
}   